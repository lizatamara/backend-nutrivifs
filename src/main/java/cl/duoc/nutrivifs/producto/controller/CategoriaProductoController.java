package cl.duoc.nutrivifs.producto.controller;

import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import cl.duoc.nutrivifs.producto.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos/categorias")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaProductoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (categoriaProductoService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(categoriaProductoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CategoriaProducto categoria){
        return new ResponseEntity<CategoriaProducto>(categoriaProductoService.registrar(categoria), HttpStatus.CREATED);
    }

}
