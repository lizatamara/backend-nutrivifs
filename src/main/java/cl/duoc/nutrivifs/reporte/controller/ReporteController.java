package cl.duoc.nutrivifs.reporte.controller;

import cl.duoc.nutrivifs.reporte.dto.ResumenDTO;
import cl.duoc.nutrivifs.reporte.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "Reportes (dashboard administrativo)")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/resumen")
    @Operation(summary = "Indicadores por período: semana, mes o anio (Administrador)")
    public ResponseEntity<ResumenDTO> resumen(@RequestParam(defaultValue = "mes") String periodo) {
        return ResponseEntity.ok(reporteService.resumen(periodo));
    }
}