package cl.duoc.nutrivifs.controller;

import cl.duoc.nutrivifs.model.CategoriaProducto;
import cl.duoc.nutrivifs.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias_productos")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping("/listar_categorias_productos")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaProductoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (categoriaProductoService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(categoriaProductoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CategoriaProducto categoriaProducto){
        return new ResponseEntity<CategoriaProducto>(categoriaProductoService.registrar(categoriaProducto), HttpStatus.CREATED);
    }

}
