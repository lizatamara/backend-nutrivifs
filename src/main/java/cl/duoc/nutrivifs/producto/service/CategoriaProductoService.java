package cl.duoc.nutrivifs.producto.service;

import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import cl.duoc.nutrivifs.producto.repository.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    public List<CategoriaProducto>listar(){
        return categoriaProductoRepository.findAll();
    }

    public CategoriaProducto buscarPorId(Long id){
        return categoriaProductoRepository.findById(id).orElseGet(null);
    }

    public CategoriaProducto registrar(CategoriaProducto categoria){
        return categoriaProductoRepository.save(categoria);
    }

}
