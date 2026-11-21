package cl.duoc.nutrivifs.boleta.model;

import cl.duoc.nutrivifs.usuario.model.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// La "orden" del frontend. Se usa @Getter/@Setter (no @Data) porque Boleta y
// DetalleBoleta se referencian entre sí y @Data generaría un toString/hashCode infinito.
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long id;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private int subtotal;
    private int descuento;
    private int total;
    @Column(nullable = false, length = 30)
    private String estado;      // Entrega pendiente, Listo, Enviado
    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleBoleta> detalles = new ArrayList<>();

}
