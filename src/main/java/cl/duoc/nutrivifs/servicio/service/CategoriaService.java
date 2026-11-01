package cl.duoc.nutrivifs.servicio.service;

import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaServicioRepository;

    public List<Categoria>listar(){
        return categoriaServicioRepository.findAll();
    }

    public Categoria buscarPorId(Long id){
        return categoriaServicioRepository.findById(id).orElseGet(null);
    }

    public Categoria registrar(Categoria categoria){
        return categoriaServicioRepository.save(categoria);
    }

}
