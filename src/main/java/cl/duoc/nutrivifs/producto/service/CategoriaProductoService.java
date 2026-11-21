package cl.duoc.nutrivifs.producto.service;

import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.producto.dto.CategoriaProductoDTO;
import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import cl.duoc.nutrivifs.producto.repository.CategoriaProductoRepository;
import cl.duoc.nutrivifs.producto.repository.ProductoRepository;
import cl.duoc.nutrivifs.util.Codigos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<CategoriaProductoDTO> listar(){
        return categoriaProductoRepository.findAll().stream().map(this::aDTO).toList();
    }

    public CategoriaProductoDTO buscarPorId(String codigo){
        return aDTO(buscar(codigo));
    }

    public CategoriaProductoDTO registrar(CategoriaProductoDTO dto){
        if (categoriaProductoRepository.existsByNombreIgnoreCase(dto.nombre().trim())) {
            throw new ReglaNegocioException("Ya existe una categoría con ese nombre.");
        }
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setNombre(dto.nombre().trim());
        categoria.setDescripcion(dto.descripcion());
        return aDTO(categoriaProductoRepository.save(categoria));
    }

    // Al renombrar, los productos siguen asociados porque apuntan al id, no al nombre
    public CategoriaProductoDTO actualizar(String codigo, CategoriaProductoDTO dto){
        CategoriaProducto categoria = buscar(codigo);
        String nombre = dto.nombre().trim();
        if (!categoria.getNombre().equalsIgnoreCase(nombre)
                && categoriaProductoRepository.existsByNombreIgnoreCase(nombre)) {
            throw new ReglaNegocioException("Ya existe una categoría con ese nombre.");
        }
        categoria.setNombre(nombre);
        categoria.setDescripcion(dto.descripcion());
        return aDTO(categoriaProductoRepository.save(categoria));
    }

    public void eliminar(String codigo){
        CategoriaProducto categoria = buscar(codigo);
        if (productoRepository.existsByCategoria_IdCategoria(categoria.getIdCategoria())) {
            throw new ReglaNegocioException("No se puede eliminar: la categoría tiene productos asociados.");
        }
        categoriaProductoRepository.delete(categoria);
    }

    private CategoriaProducto buscar(String codigo){
        return categoriaProductoRepository.findById(Codigos.aId(codigo))
                .orElseThrow(() -> new NoEncontradoException("Categoría no encontrada: " + codigo));
    }

    private CategoriaProductoDTO aDTO(CategoriaProducto c){
        return new CategoriaProductoDTO(Codigos.formatear("CAT", 2, c.getIdCategoria()),
                c.getNombre(), c.getDescripcion());
    }

}
