package cl.duoc.nutrivifs.servicio.controller;

import cl.duoc.nutrivifs.servicio.dto.ServicioDTO;
import cl.duoc.nutrivifs.servicio.service.ServicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/servicios")
@Tag(name = "Servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    @Operation(summary = "Lista los servicios de la clínica (público)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(servicioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de un servicio por código SE001 o id (público)")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(servicioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un servicio (Administrador)")
    public ResponseEntity<?> registrar(@Valid @RequestBody ServicioDTO servicio){
        return new ResponseEntity<ServicioDTO>(servicioService.registrar(servicio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un servicio (Administrador)")
    public ResponseEntity<?> actualizar(@PathVariable String id, @Valid @RequestBody ServicioDTO servicio){
        return ResponseEntity.ok(servicioService.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un servicio (Administrador)")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
