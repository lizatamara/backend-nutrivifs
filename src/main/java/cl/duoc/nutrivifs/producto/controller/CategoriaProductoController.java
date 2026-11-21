package cl.duoc.nutrivifs.producto.controller;

import cl.duoc.nutrivifs.producto.dto.CategoriaProductoDTO;
import cl.duoc.nutrivifs.producto.service.CategoriaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos/categorias")
@Tag(name = "Categorías de productos")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    @Operation(summary = "Lista las categorías (público)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaProductoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de una categoría por código CAT01 o id (público)")
    public ResponseEntity<?> buscarPorId(@PathVariable String id){
        return ResponseEntity.ok(categoriaProductoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea una categoría (Administrador)")
    public ResponseEntity<?> registrar(@Valid @RequestBody CategoriaProductoDTO categoria){
        return new ResponseEntity<CategoriaProductoDTO>(categoriaProductoService.registrar(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría (Administrador)")
    public ResponseEntity<?> actualizar(@PathVariable String id, @Valid @RequestBody CategoriaProductoDTO categoria){
        return ResponseEntity.ok(categoriaProductoService.actualizar(id, categoria));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoría sin productos (Administrador)")
    public ResponseEntity<?> eliminar(@PathVariable String id){
        categoriaProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
