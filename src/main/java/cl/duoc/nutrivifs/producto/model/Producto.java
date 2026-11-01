package cl.duoc.nutrivifs.producto.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    @Column(nullable = false, length = 30)
    private String nombre;
    private String descripcion;
    private String especies;
    private int precio;
    private int stock;
    @Column(name = "stock_critico")
    // igual spring boot lo hace automatico al cambio de nombre, no se por qué hay que hacerlo
    private int stockCritico;
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    //pendiente: generar el codigo alfanumerico a partir del id (ejemplo M003)
    //lo hacemos desde los dto que aun no implementamos

}
