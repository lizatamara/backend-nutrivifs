package cl.duoc.nutrivifs.producto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias_productos")
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;
    @Column(nullable = false, unique = true, length = 30)
    private String nombre;
    // el panel de administración del frontend permite escribir una descripción
    @Column(length = 200)
    private String descripcion;

}
