package cl.duoc.nutrivifs.reporte.dto;

import java.time.LocalDate;
import java.util.List;

// Indicadores del dashboard para un período
public record ResumenDTO(
        String periodo,
        LocalDate desde,
        LocalDate hasta,
        long totalOperaciones,      // boletas del período
        long montoTotal,            // suma de totales vendidos
        long unidadesVendidas,      // suma de cantidades
        long citasSolicitadas,      // citas registradas en el período
        long citasConfirmadas,      // servicios realizados/confirmados
        List<ProductoTopDTO> masSolicitados) {
}