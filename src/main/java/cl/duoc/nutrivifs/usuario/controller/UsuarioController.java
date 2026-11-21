package cl.duoc.nutrivifs.usuario.controller;

import cl.duoc.nutrivifs.usuario.dto.UsuarioDTO;
import cl.duoc.nutrivifs.usuario.dto.UsuarioRequest;
import cl.duoc.nutrivifs.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Lista los usuarios (Administrador)")
    public ResponseEntity<?>listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de un usuario (Administrador)")
    public ResponseEntity<?>buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un usuario con cualquier rol (Administrador)")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRequest usuario){
        return new ResponseEntity<UsuarioDTO>(usuarioService.registrar(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario (Administrador)")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest usuario,
                                        Authentication auth){
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario, auth.getName()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario sin compras (Administrador)")
    public ResponseEntity<?> eliminar(@PathVariable Long id, Authentication auth){
        usuarioService.eliminar(id, auth.getName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquea una cuenta bloqueada por intentos fallidos (Administrador)")
    public ResponseEntity<?> desbloquear(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.desbloquear(id));
    }

    @PutMapping("/me")
    @Operation(summary = "Edita el perfil del usuario conectado (cualquier rol)")
    public ResponseEntity<?> editarMiPerfil(@Valid @RequestBody UsuarioRequest usuario, Authentication auth){
        return ResponseEntity.ok(usuarioService.actualizarPerfil(auth.getName(), usuario));
    }

}
