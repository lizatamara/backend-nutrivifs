package cl.duoc.nutrivifs.boleta.dto;

import java.time.LocalDateTime;
import java.util.List;

// Boleta con la misma forma que la interfaz Orden del frontend
public record BoletaDTO(
        String id,              // "ORD-0001"
        LocalDateTime date,
        ClienteDTO customer,
        List<ItemBoletaDTO> items,
        Integer subtotal,
        Integer discount,
        Integer total,
        String status) {
}
