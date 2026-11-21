package cl.duoc.nutrivifs.servicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;
    @Column(nullable = false, length = 100)
    private String nombre;
    private String descripcion;
    private String especie; // falta normalizar
    private int duracion; // minutos; el frontend lo muestra como "30 min"
    private String observacion;
    private int precio;
    // valor que el frontend envía a "Agenda tu consulta" para dejar preseleccionado el tipo de consulta
    private String consulta;
    private String image;
    private String alt;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private boolean enlace;

    // el código alfanumérico (SE001) se genera a partir del id en ServicioService (ver ServicioDTO)

}
