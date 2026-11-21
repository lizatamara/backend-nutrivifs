package cl.duoc.nutrivifs.servicio.controller;

import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/servicios/categorias")
@Tag(name = "Categorías de servicios")
public class CategoriaServicioController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Lista las categorías de servicios (público)")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalle de una categoría de servicio (público)")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crea una categoría de servicio (Administrador)")
    public ResponseEntity<?> registrar(@RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.registrar(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría de servicio (Administrador)")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoría sin servicios (Administrador)")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
