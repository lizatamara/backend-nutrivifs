package cl.duoc.nutrivifs.service;

import cl.duoc.nutrivifs.model.CategoriaProducto;
import cl.duoc.nutrivifs.repository.CategoriaProductoRepository;
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

    public CategoriaProducto registrar(CategoriaProducto categoriaProducto){
        return categoriaProductoRepository.save(categoriaProducto);
    }

}
