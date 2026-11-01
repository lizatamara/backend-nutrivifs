package cl.duoc.nutrivifs.usuario.controller;

import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?>listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Long id) {
        if (usuarioService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(usuarioService.buscarPorId(id));

    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
        return new ResponseEntity<Usuario>(usuarioService.registrar(usuario), HttpStatus.CREATED);
    }
    
}
