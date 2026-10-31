package cl.duoc.nutrivifs.service;

import cl.duoc.nutrivifs.model.Servicio;
import cl.duoc.nutrivifs.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio>listar(){
        return servicioRepository.findAll();
    }

    public Servicio buscarPorId(Long id){
        return servicioRepository.findById(id).orElseGet(null);
    }

    public Servicio registrar(Servicio servicio){
        return servicioRepository.save(servicio);
    }

}

