package cl.duoc.nutrivifs.servicio.service;

import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.repository.CategoriaRepository;
import cl.duoc.nutrivifs.servicio.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Categoria>listar(){
        return categoriaServicioRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
        return categoriaServicioRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Categoría de servicio no encontrada: " + id));
    }

    public Categoria registrar(Categoria categoria){
        categoria.setIdCategoria(null);   // el id lo asigna la BD
        return categoriaServicioRepository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria datos){
        Categoria categoria = buscarPorId(id);
        categoria.setNombre(datos.getNombre());
        return categoriaServicioRepository.save(categoria);
    }

    public void eliminar(Long id){
        Categoria categoria = buscarPorId(id);
        if (servicioRepository.existsByCategoria_IdCategoria(id)) {
            throw new ReglaNegocioException("No se puede eliminar: la categoría tiene servicios asociados.");
        }
        categoriaServicioRepository.delete(categoria);
    }

}
