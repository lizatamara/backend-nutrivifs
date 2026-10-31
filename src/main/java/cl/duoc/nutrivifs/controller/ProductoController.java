package cl.duoc.nutrivifs.controller;

import cl.duoc.nutrivifs.model.CategoriaProducto;
import cl.duoc.nutrivifs.model.Producto;
import cl.duoc.nutrivifs.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar_productos")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (productoService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Producto producto){
        return new ResponseEntity<Producto>(productoService.registrar(producto), HttpStatus.CREATED);
    }


}

