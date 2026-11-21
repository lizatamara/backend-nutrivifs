package cl.duoc.nutrivifs.cita.dto;
import jakarta.validation.constraints.NotBlank;
public record EstadoCitaRequest(@NotBlank String estado) {}
