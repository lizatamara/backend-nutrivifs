package cl.duoc.nutrivifs.cita.controller;
import cl.duoc.nutrivifs.cita.dto.*; import cl.duoc.nutrivifs.cita.service.CitaService;
import jakarta.validation.Valid; import org.springframework.http.HttpStatus; import org.springframework.http.ResponseEntity; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/citas")
public class CitaController {
 private final CitaService service; public CitaController(CitaService service){this.service=service;}
 @PostMapping public ResponseEntity<CitaDTO> crear(@Valid @RequestBody CitaRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));}
 @GetMapping public ResponseEntity<?> listar(){return ResponseEntity.ok(service.listar());}
 @GetMapping("/mias") public ResponseEntity<?> mias(Authentication auth){return ResponseEntity.ok(service.mias(auth.getName()));}
 @PatchMapping("/{id}/estado") public ResponseEntity<?> estado(@PathVariable String id,@Valid @RequestBody EstadoCitaRequest r){return ResponseEntity.ok(service.cambiarEstado(id,r.estado()));}
}
