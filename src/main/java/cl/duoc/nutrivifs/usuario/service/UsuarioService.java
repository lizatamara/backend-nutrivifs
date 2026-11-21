package cl.duoc.nutrivifs.usuario.service;

import cl.duoc.nutrivifs.boleta.repository.BoletaRepository;
import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.usuario.dto.UsuarioDTO;
import cl.duoc.nutrivifs.usuario.dto.UsuarioRequest;
import cl.duoc.nutrivifs.usuario.model.Rol;
import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.repository.UsuarioRepository;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Registro, CRUD de usuarios y edición del perfil propio
@Service
public class UsuarioService {

    private static final int EDAD_MINIMA = 14;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(this::aDTO).toList();
    }

    public UsuarioDTO buscarPorId(Long id){
        return aDTO(buscar(id));
    }

    public Usuario buscarPorMail(String mail){
        return usuarioRepository.findByMail(mail.trim().toLowerCase())
                .orElseThrow(() -> new NoEncontradoException("Usuario no encontrado."));
    }

    // Registro público (/auth/registro): siempre queda como Cliente, aunque el JSON traiga otro rol
    public UsuarioDTO registrarCliente(UsuarioRequest req){
        return aDTO(crear(req, rolService.buscarPorNombre(Rol.CLIENTE)));
    }

    // Creación desde el panel (solo Administrador): puede asignar cualquier rol
    public UsuarioDTO registrar(UsuarioRequest req){
        if (req.role() == null || req.role().isBlank()) {
            throw new ReglaNegocioException("Selecciona el tipo de usuario.");
        }
        return aDTO(crear(req, rolService.buscarPorNombre(req.role())));
    }

    public UsuarioDTO actualizar(Long id, UsuarioRequest req, String mailDeQuienEdita){
        Usuario usuario = buscar(id);
        copiarDatos(req, usuario);
        if (req.role() != null && !req.role().isBlank()) {
            Rol nuevo = rolService.buscarPorNombre(req.role());
            if (usuario.getMail().equals(mailDeQuienEdita) && !nuevo.getId().equals(usuario.getRol().getId())) {
                throw new ReglaNegocioException("No puedes cambiar tu propio rol.");
            }
            usuario.setRol(nuevo);
        }
        cambiarPasswordSiViene(req.password(), usuario);
        return aDTO(usuarioRepository.save(usuario));
    }

    // Mi perfil: cada usuario edita sus datos, nunca su rol
    public UsuarioDTO actualizarPerfil(String mail, UsuarioRequest req){
        Usuario usuario = buscarPorMail(mail);
        copiarDatos(req, usuario);
        cambiarPasswordSiViene(req.password(), usuario);
        return aDTO(usuarioRepository.save(usuario));
    }

    public void eliminar(Long id, String mailDeQuienElimina){
        Usuario usuario = buscar(id);
        if (usuario.getMail().equals(mailDeQuienElimina)) {
            throw new ReglaNegocioException("No puedes eliminar tu propia cuenta.");
        }
        if (boletaRepository.existsByUsuario_Id(id)) {
            throw new ReglaNegocioException("No se puede eliminar: el usuario tiene compras registradas.");
        }
        usuarioRepository.delete(usuario);
    }

    // Desbloquea una cuenta bloqueada por 3 intentos fallidos (Administrador)
    public UsuarioDTO desbloquear(Long id){
        Usuario usuario = buscar(id);
        usuario.setBloqueado(false);
        usuario.setIntentosFallidos(0);
        return aDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO aDTO(Usuario u){
        String rol = u.getRol() == null ? null : u.getRol().getNombre();
        return new UsuarioDTO(u.getId(), u.getRun(), u.getNombre(), u.getApellido(), u.getMail(), rol,
                u.getFechaNacimiento(), u.getDireccion(), u.getRegion(), u.getComuna(), u.getTelefono(),
                u.isBloqueado());
    }

    private Usuario crear(UsuarioRequest req, Rol rol){
        String mail = req.email().trim().toLowerCase();
        if (usuarioRepository.existsByMail(mail)) {
            throw new ReglaNegocioException("Ese correo ya está registrado.");
        }
        if (usuarioRepository.existsByRun(req.run().trim().toUpperCase())) {
            throw new ReglaNegocioException("Ya existe un usuario con este RUN.");
        }
        validarPassword(req.password());
        Usuario usuario = new Usuario();
        copiarDatos(req, usuario);
        usuario.setMail(mail);
        usuario.setRol(rol);
        usuario.setPassword(passwordEncoder.encode(req.password()));   // nunca en texto plano
        return usuarioRepository.save(usuario);
    }

    private void copiarDatos(UsuarioRequest req, Usuario usuario){
        if (req.birthDate() != null
                && Period.between(req.birthDate(), LocalDate.now()).getYears() < EDAD_MINIMA) {
            throw new ReglaNegocioException("Debes tener al menos " + EDAD_MINIMA + " años.");
        }
        usuario.setRun(req.run().trim().toUpperCase());
        usuario.setNombre(req.name().trim());
        usuario.setApellido(req.apellido().trim());
        usuario.setFechaNacimiento(req.birthDate());
        usuario.setDireccion(req.direccion().trim());
        usuario.setRegion(req.region());
        usuario.setComuna(req.comuna());
        usuario.setTelefono(req.phone());
    }

    private void cambiarPasswordSiViene(String password, Usuario usuario){
        if (password != null && !password.isBlank()) {
            validarPassword(password);
            usuario.setPassword(passwordEncoder.encode(password));
        }
    }

    // Misma regla del frontend: entre 4 y 10 caracteres y al menos una mayúscula
    private void validarPassword(String password){
        if (password == null || password.length() < 4 || password.length() > 10
                || !password.matches(".*[A-Z].*")) {
            throw new ReglaNegocioException("La contraseña debe tener entre 4 y 10 caracteres y una mayúscula.");
        }
    }

    private Usuario buscar(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Usuario no encontrado: " + id));
    }

}
