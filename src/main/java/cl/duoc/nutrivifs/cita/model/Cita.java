package cl.duoc.nutrivifs.cita.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter; import lombok.Setter; import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
@Entity @Table(name="citas")
public class Cita {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id_cita")
    private Long id;
    @Column(nullable=false, length=120) private String nombre;
    @Column(nullable=false, length=20) private String rut;
    @Column(nullable=false, length=120) private String correo;
    @Column(nullable=false, length=30) private String telefono;
    @Column(nullable=false, length=100) private String mascota;
    @Column(nullable=false, length=60) private String especie;
    @Column(name="tipo_consulta", nullable=false, length=120) private String tipoConsulta;
    @Column(nullable=false, length=10) private String fecha;
    @Column(nullable=false, length=1000) private String motivo;
    @Column(nullable=false, length=20) private String estado="Pendiente";
    @Column(name="fecha_solicitud", nullable=false) private LocalDateTime fechaSolicitud=LocalDateTime.now();
}
