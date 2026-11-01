package cl.duoc.nutrivifs.usuario.controller;

import cl.duoc.nutrivifs.usuario.model.Rol;
import cl.duoc.nutrivifs.usuario.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(rolService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (rolService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(rolService.buscarPorId(id));
    }

}
