package cl.duoc.nutrivifs.producto.controller;

import cl.duoc.nutrivifs.producto.model.Categoria;
import cl.duoc.nutrivifs.producto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias_productos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar_categorias_productos")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (categoriaService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.registrar(categoria), HttpStatus.CREATED);
    }

}
