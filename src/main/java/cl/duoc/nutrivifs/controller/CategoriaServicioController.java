package cl.duoc.nutrivifs.controller;

import cl.duoc.nutrivifs.model.CategoriaServicio;
import cl.duoc.nutrivifs.service.CategoriaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias_servicios")
public class CategoriaServicioController {

    @Autowired
    private CategoriaServicioService categoriaServicioService;

    @GetMapping("/listar_categorias_servicios")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(categoriaServicioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if (categoriaServicioService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(categoriaServicioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CategoriaServicio categoriaServicio){
        return new ResponseEntity<CategoriaServicio>(categoriaServicioService.registrar(categoriaServicio), HttpStatus.CREATED);
    }

}
