package cl.duoc.nutrivifs.usuario.service;

import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.usuario.model.Rol;
import cl.duoc.nutrivifs.usuario.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public Rol buscarPorId(Long id){
        return rolRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Rol no encontrado: " + id));
    }

    // "administrador", "Vendedor"... -> entidad Rol
    public Rol buscarPorNombre(String nombre){
        return rolRepository.findByNombreIgnoreCase(nombre == null ? "" : nombre.trim())
                .orElseThrow(() -> new ReglaNegocioException("Rol inválido: " + nombre));
    }

}
