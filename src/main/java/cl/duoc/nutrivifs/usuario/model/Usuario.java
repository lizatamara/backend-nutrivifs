package cl.duoc.nutrivifs.usuario.model;

import jakarta.persistence.*;
import java.time.LocalDate;
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
    @Column(unique = true, length = 12)
    private String run;
    private String nombre;
    private String apellido;
    @Column(nullable = false, unique = true, length = 100)
    private String mail;
    private String password;          // siempre cifrada con BCrypt
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    // Datos del perfil que pide el formulario de registro del frontend
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String direccion;
    private String region;
    private String comuna;
    private String telefono;

    // Bloqueo tras 3 intentos fallidos de inicio de sesión
    @Column(name = "intentos_fallidos")
    private int intentosFallidos;
    private boolean bloqueado;

}
