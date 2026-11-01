package cl.duoc.nutrivifs.servicio.controller;

import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias_servicios")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listar_categorias_servicios")
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
