package cl.duoc.nutrivifs.servicio.service;

import cl.duoc.nutrivifs.exception.NoEncontradoException;
import cl.duoc.nutrivifs.exception.ReglaNegocioException;
import cl.duoc.nutrivifs.servicio.dto.ServicioDTO;
import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.model.Servicio;
import cl.duoc.nutrivifs.servicio.repository.CategoriaRepository;
import cl.duoc.nutrivifs.servicio.repository.ServicioRepository;
import cl.duoc.nutrivifs.util.Codigos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ServicioDTO>listar(){
        return servicioRepository.findAll().stream().map(this::aDTO).toList();
    }

    public ServicioDTO buscarPorId(String codigo){
        return aDTO(buscar(codigo));
    }

    public ServicioDTO registrar(ServicioDTO dto){
        Servicio servicio = new Servicio();
        copiar(dto, servicio);
        return aDTO(servicioRepository.save(servicio));
    }

    public ServicioDTO actualizar(String codigo, ServicioDTO dto){
        Servicio servicio = buscar(codigo);
        copiar(dto, servicio);
        return aDTO(servicioRepository.save(servicio));
    }

    public void eliminar(String codigo){
        servicioRepository.delete(buscar(codigo));
    }

    private Servicio buscar(String codigo){
        return servicioRepository.findById(Codigos.aId(codigo))
                .orElseThrow(() -> new NoEncontradoException("Servicio no encontrado: " + codigo));
    }

    private void copiar(ServicioDTO dto, Servicio servicio){
        Categoria categoria = categoriaRepository.findByNombre(dto.categoria())
                .orElseThrow(() -> new ReglaNegocioException("La categoría no existe: " + dto.categoria()));
        servicio.setNombre(dto.nombre().trim());
        servicio.setDescripcion(dto.descripcion());
        servicio.setEspecie(dto.especie());
        servicio.setDuracion(dto.duracion());
        servicio.setPrecio(dto.precio());
        servicio.setObservacion(dto.observacion());
        servicio.setImage(dto.imagen());
        servicio.setAlt(dto.alt());
        servicio.setConsulta(dto.consulta());
        servicio.setEnlace(dto.enlace());
        servicio.setCategoria(categoria);
    }

    private ServicioDTO aDTO(Servicio s){
        String categoria = s.getCategoria() == null ? null : s.getCategoria().getNombre();
        return new ServicioDTO(Codigos.formatear("SE", 3, s.getIdServicio()), s.getNombre(),
                s.getDescripcion(), s.getEspecie(), s.getDuracion(), s.getPrecio(), s.getObservacion(),
                s.getImage(), s.getAlt(), s.getConsulta(), s.isEnlace(), categoria);
    }

}
