package cl.duoc.nutrivifs.reporte.service;

import cl.duoc.nutrivifs.boleta.repository.BoletaRepository;
import cl.duoc.nutrivifs.boleta.repository.DetalleBoletaRepository;
import cl.duoc.nutrivifs.cita.repository.CitaRepository;
import cl.duoc.nutrivifs.reporte.dto.ProductoTopDTO;
import cl.duoc.nutrivifs.reporte.dto.ResumenDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Calcula los indicadores del dashboard con datos reales de la base de datos
@Service
public class ReporteService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private DetalleBoletaRepository detalleRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Transactional(readOnly = true)
    public ResumenDTO resumen(String periodo) {
        String p = periodo == null ? "mes" : periodo.trim().toLowerCase();
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = switch (p) {
            case "semana" -> hoy.minusDays(6);      // últimos 7 días
            case "mes" -> hoy.withDayOfMonth(1);    // mes en curso
            case "anio" -> hoy.withDayOfYear(1);    // año en curso
            default -> throw new IllegalArgumentException("Período no válido. Usa semana, mes o anio.");
        };

        LocalDateTime desde = inicio.atStartOfDay();
        LocalDateTime hasta = hoy.atTime(LocalTime.MAX);

        List<ProductoTopDTO> top = detalleRepository.masVendidosEntre(desde, hasta, PageRequest.of(0, 5));

        return new ResumenDTO(
                p, inicio, hoy,
                boletaRepository.countByFechaBetween(desde, hasta),
                boletaRepository.sumarTotalEntre(desde, hasta),
                detalleRepository.sumarUnidadesEntre(desde, hasta),
                citaRepository.countByFechaSolicitudBetween(desde, hasta),
                citaRepository.countByEstadoAndFechaSolicitudBetween("Confirmada", desde, hasta),
                top);
    }
}