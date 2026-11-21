package cl.duoc.nutrivifs.usuario.controller;

import cl.duoc.nutrivifs.usuario.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios/roles")
@Tag(name = "Roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "Lista los roles (Administrador)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(rolService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de un rol (Administrador)")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }

}
