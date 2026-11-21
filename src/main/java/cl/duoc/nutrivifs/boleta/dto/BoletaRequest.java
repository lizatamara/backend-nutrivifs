package cl.duoc.nutrivifs.boleta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

// Compra completa: productos del carrito y cupón (opcional)
public record BoletaRequest(
        @NotEmpty List<@Valid ItemRequest> items,
        String cupon) {
}
