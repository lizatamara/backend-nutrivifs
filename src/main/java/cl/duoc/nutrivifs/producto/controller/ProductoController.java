package cl.duoc.nutrivifs.producto.controller;

import cl.duoc.nutrivifs.producto.dto.ProductoDTO;
import cl.duoc.nutrivifs.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Lista los productos (público)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de un producto por código ME001 o id (público)")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea un producto (Administrador)")
    public ResponseEntity<?> registrar(@Valid @RequestBody ProductoDTO producto){
        return new ResponseEntity<ProductoDTO>(productoService.registrar(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto (Administrador)")
    public ResponseEntity<?> actualizar(@PathVariable String id, @Valid @RequestBody ProductoDTO producto){
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto que no esté en boletas (Administrador)")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
