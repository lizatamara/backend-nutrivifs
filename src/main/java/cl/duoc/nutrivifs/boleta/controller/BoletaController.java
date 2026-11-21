package cl.duoc.nutrivifs.boleta.controller;

import cl.duoc.nutrivifs.boleta.dto.BoletaDTO;
import cl.duoc.nutrivifs.boleta.dto.BoletaRequest;
import cl.duoc.nutrivifs.boleta.dto.EstadoRequest;
import cl.duoc.nutrivifs.boleta.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boletas")
@Tag(name = "Boletas (órdenes)")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @PostMapping
    @Operation(summary = "Compra del usuario conectado: descuenta stock y aplica cupón (con sesión)")
    public ResponseEntity<?> comprar(@Valid @RequestBody BoletaRequest compra, Authentication auth){
        return new ResponseEntity<BoletaDTO>(boletaService.comprar(auth.getName(), compra), HttpStatus.CREATED);
    }

    @GetMapping("/mias")
    @Operation(summary = "Historial de compras del usuario conectado (con sesión)")
    public ResponseEntity<?> misBoletas(Authentication auth){
        return ResponseEntity.ok(boletaService.misBoletas(auth.getName()));
    }

    @GetMapping
    @Operation(summary = "Lista todas las boletas (Administrador y Vendedor)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(boletaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de una boleta por ORD-0001 o id (Administrador y Vendedor)")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(boletaService.buscarPorId(id));
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambia el estado de entrega (Administrador)")
    public ResponseEntity<?> cambiarEstado(@PathVariable String id, @Valid @RequestBody EstadoRequest req){
        return ResponseEntity.ok(boletaService.cambiarEstado(id, req.estado()));
    }

}
