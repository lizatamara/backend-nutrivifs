package cl.duoc.nutrivifs.resena.controller;

import cl.duoc.nutrivifs.resena.dto.ResenaDTO;
import cl.duoc.nutrivifs.resena.dto.ResenaRequest;
import cl.duoc.nutrivifs.resena.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {
    private final ResenaService service;

    public ResenaController(ResenaService service) {
        this.service = service;
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ResenaDTO>> listar(@PathVariable String productoId) {
        return ResponseEntity.ok(service.listar(productoId));
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> guardar(@Valid @RequestBody ResenaRequest request,
                                              Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.guardar(request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id, Authentication authentication) {
        boolean administrador = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMINISTRADOR"::equals);
        service.eliminar(id, authentication.getName(), administrador);
        return ResponseEntity.noContent().build();
    }
}
