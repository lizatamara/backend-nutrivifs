package cl.duoc.nutrivifs.producto.service;

import cl.duoc.nutrivifs.producto.model.Categoria;
import cl.duoc.nutrivifs.producto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria>listar(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id).orElseGet(null);
    }

    public Categoria registrar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

}
