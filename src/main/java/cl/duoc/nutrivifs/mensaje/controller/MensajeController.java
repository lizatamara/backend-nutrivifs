package cl.duoc.nutrivifs.mensaje.controller;
import cl.duoc.nutrivifs.mensaje.dto.MensajeRequest; import cl.duoc.nutrivifs.mensaje.service.MensajeService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/mensajes") public class MensajeController {
 private final MensajeService service; public MensajeController(MensajeService service){this.service=service;}
 @PostMapping public ResponseEntity<?> crear(@Valid @RequestBody MensajeRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));}
 @GetMapping public ResponseEntity<?> listar(){return ResponseEntity.ok(service.listar());}
 @DeleteMapping("/{id}") public ResponseEntity<?> eliminar(@PathVariable Long id){service.eliminar(id);return ResponseEntity.noContent().build();}
}
