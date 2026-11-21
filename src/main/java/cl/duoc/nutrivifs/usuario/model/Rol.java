package cl.duoc.nutrivifs.usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {

    // Nombres usados por el sistema (los mismos que el frontend)
    public static final String ADMINISTRADOR = "Administrador";
    public static final String VENDEDOR = "Vendedor";
    public static final String CLIENTE = "Cliente";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roles")
    private Long id;
    @Column(nullable = false, unique = true, length = 30)
    private String nombre;

}
