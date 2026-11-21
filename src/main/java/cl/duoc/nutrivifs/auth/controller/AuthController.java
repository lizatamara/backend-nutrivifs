package cl.duoc.nutrivifs.auth.controller;

import cl.duoc.nutrivifs.auth.dto.LoginRequest;
import cl.duoc.nutrivifs.auth.service.AuthService;
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
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión y entrega el token JWT (público)")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/registro")
    @Operation(summary = "Registro público: siempre crea un Cliente")
    public ResponseEntity<?> registro(@Valid @RequestBody UsuarioRequest req){
        return new ResponseEntity<UsuarioDTO>(usuarioService.registrarCliente(req), HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Entrega un token nuevo si el actual sigue vigente (con sesión)")
    public ResponseEntity<?> renovar(Authentication auth){
        return ResponseEntity.ok(authService.renovar(auth.getName()));
    }

    @GetMapping("/me")
    @Operation(summary = "Datos del usuario conectado (con sesión)")
    public ResponseEntity<?> yo(Authentication auth){
        return ResponseEntity.ok(usuarioService.aDTO(usuarioService.buscarPorMail(auth.getName())));
    }

}
