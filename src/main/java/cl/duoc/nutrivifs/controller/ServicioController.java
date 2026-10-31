package cl.duoc.nutrivifs.controller;

import cl.duoc.nutrivifs.model.CategoriaServicio;
import cl.duoc.nutrivifs.model.Servicio;
import cl.duoc.nutrivifs.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listar_servicios")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(servicioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (servicioService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(servicioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Servicio servicio){
        return new ResponseEntity<Servicio>(servicioService.registrar(servicio), HttpStatus.CREATED);
    }


}

