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
    private int duracion; // hay que convertir minutos a string de texto de la hora
    private String observacion;
    private int precio;
    private String consulta; // no entiendo para que sirve pero en el front está
    private String image;
    private String alt;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private boolean enlace;

    //pendiente: generar el codigo alfanumerico a partir del id (ejemplo M003)
    //lo hacemos desde los dto que aun no implementamos

}


/*

export interface Servicio {

}

*/
 