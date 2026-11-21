package cl.duoc.nutrivifs.auth.service;

import cl.duoc.nutrivifs.auth.dto.AuthResponse;
import cl.duoc.nutrivifs.auth.dto.LoginRequest;
import cl.duoc.nutrivifs.exception.CredencialesInvalidasException;
import cl.duoc.nutrivifs.security.JwtService;
import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.repository.UsuarioRepository;
import cl.duoc.nutrivifs.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Inicio de sesión con BCrypt, bloqueo tras 3 intentos fallidos y renovación del token
@Service
public class AuthService {

    private static final int MAX_INTENTOS = 3;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest req){
        Usuario usuario = usuarioRepository.findByMail(req.email().trim().toLowerCase())
                .orElseThrow(() -> new CredencialesInvalidasException("Correo o contraseña incorrectos."));

        if (usuario.isBloqueado()) {
            throw new CredencialesInvalidasException("Cuenta bloqueada por 3 intentos fallidos. Contacta a soporte.");
        }

        if (!passwordEncoder.matches(req.password(), usuario.getPassword())) {
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
            if (usuario.getIntentosFallidos() >= MAX_INTENTOS) {
                usuario.setBloqueado(true);
            }
            usuarioRepository.save(usuario);
            throw new CredencialesInvalidasException(usuario.isBloqueado()
                    ? "Cuenta bloqueada por 3 intentos fallidos. Contacta a soporte."
                    : "Correo o contraseña incorrectos. Intentos restantes: "
                      + (MAX_INTENTOS - usuario.getIntentosFallidos()));
        }

        usuario.setIntentosFallidos(0);
        usuarioRepository.save(usuario);
        return new AuthResponse(jwtService.generarToken(usuario), usuarioService.aDTO(usuario));
    }

    // Con un token todavía vigente se entrega uno nuevo (renovación)
    public AuthResponse renovar(String mail){
        Usuario usuario = usuarioService.buscarPorMail(mail);
        return new AuthResponse(jwtService.generarToken(usuario), usuarioService.aDTO(usuario));
    }

}
