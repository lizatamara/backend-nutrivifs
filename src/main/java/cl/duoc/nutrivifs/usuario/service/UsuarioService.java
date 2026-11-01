package cl.duoc.nutrivifs.usuario.service;

import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseGet(null);
    }

    public Usuario registrar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
