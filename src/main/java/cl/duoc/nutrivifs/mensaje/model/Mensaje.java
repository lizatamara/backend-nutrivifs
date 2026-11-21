package cl.duoc.nutrivifs.mensaje.model;
import jakarta.persistence.*; import java.time.LocalDateTime; import lombok.Getter; import lombok.Setter; import lombok.NoArgsConstructor;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="mensajes")
public class Mensaje {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id_mensaje") private Long id;
 @Column(nullable=false,length=100) private String nombre; @Column(nullable=false,length=100) private String correo;
 @Column(nullable=false,length=30) private String motivo; @Column(nullable=false,length=500) private String comentario;
 @Column(nullable=false) private LocalDateTime fecha=LocalDateTime.now();
}
