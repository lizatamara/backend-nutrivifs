package cl.duoc.nutrivifs.service;

import cl.duoc.nutrivifs.model.CategoriaServicio;
import cl.duoc.nutrivifs.repository.CategoriaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioService {

    @Autowired
    private CategoriaServicioRepository categoriaServicioRepository;

    public List<CategoriaServicio>listar(){
        return categoriaServicioRepository.findAll();
    }

    public CategoriaServicio buscarPorId(Long id){
        return categoriaServicioRepository.findById(id).orElseGet(null);
    }

    public CategoriaServicio registrar(CategoriaServicio categoriaServicio){
        return categoriaServicioRepository.save(categoriaServicio);
    }

}
