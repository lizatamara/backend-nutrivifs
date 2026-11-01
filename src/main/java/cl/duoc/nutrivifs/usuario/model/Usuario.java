package cl.duoc.nutrivifs.usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_usuario")
    private Long id;
    private String run;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

}
