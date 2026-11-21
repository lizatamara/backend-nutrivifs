package cl.duoc.nutrivifs.boleta.model;

import cl.duoc.nutrivifs.producto.model.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Una línea de la boleta: qué producto, cuántas unidades y a qué precio se vendió
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detalles_boleta")
public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_boleta")
    private Boleta boleta;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto")
    private Producto producto;
    private int cantidad;
    @Column(name = "precio_unitario")
    private int precioUnitario;   // precio al momento de la compra
    private int subtotal;

}
