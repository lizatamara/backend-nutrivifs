package cl.duoc.nutrivifs.resena.model;
import jakarta.persistence.*; import java.time.LocalDateTime; import lombok.Getter; import lombok.Setter; import lombok.NoArgsConstructor;
@Getter @Setter @NoArgsConstructor @Entity @Table(name="resenas", uniqueConstraints=@UniqueConstraint(columnNames={"producto_codigo","correo"}))
public class Resena {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id_resena") private Long id;
 @Column(name="producto_codigo",nullable=false,length=30) private String productoId;
 @Column(nullable=false,length=120) private String correo; @Column(nullable=false,length=100) private String autor;
 @Column(nullable=false) private int puntuacion; @Column(nullable=false,length=300) private String comentario;
 @Column(nullable=false) private LocalDateTime fecha=LocalDateTime.now();
}
