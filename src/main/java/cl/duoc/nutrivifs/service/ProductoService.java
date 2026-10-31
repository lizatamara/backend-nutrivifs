package cl.duoc.nutrivifs.service;

import cl.duoc.nutrivifs.model.Producto;
import cl.duoc.nutrivifs.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto>listar(){
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id){
        return productoRepository.findById(id).orElseGet(null);
    }

    public Producto registrar(Producto producto){
        return productoRepository.save(producto);
    }

}

