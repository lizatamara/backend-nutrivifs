package cl.duoc.nutrivifs.config;

import cl.duoc.nutrivifs.producto.model.CategoriaProducto;
import cl.duoc.nutrivifs.producto.model.Producto;
import cl.duoc.nutrivifs.producto.repository.CategoriaProductoRepository;
import cl.duoc.nutrivifs.producto.repository.ProductoRepository;
import cl.duoc.nutrivifs.servicio.model.Categoria;
import cl.duoc.nutrivifs.servicio.model.Servicio;
import cl.duoc.nutrivifs.servicio.repository.CategoriaRepository;
import cl.duoc.nutrivifs.servicio.repository.ServicioRepository;
import cl.duoc.nutrivifs.usuario.model.Rol;
import cl.duoc.nutrivifs.usuario.model.Usuario;
import cl.duoc.nutrivifs.usuario.repository.RolRepository;
import cl.duoc.nutrivifs.usuario.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// Al iniciar el backend, si una tabla está vacía se cargan sus datos base
// (los mismos del frontend en src/datos/ y de scripts_poblado.sql).
// Las contraseñas se guardan cifradas con BCrypt, por eso los usuarios se crean desde Java.
@Configuration
public class DatosIniciales {

    @Autowired private RolRepository rolRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private CategoriaProductoRepository categoriaProductoRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private CategoriaRepository categoriaServicioRepository;
    @Autowired private ServicioRepository servicioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner cargarDatos() {
        return args -> {
            if (rolRepository.count() == 0) {
                for (String nombre : List.of(Rol.ADMINISTRADOR, Rol.VENDEDOR, Rol.CLIENTE)) {
                    Rol rol = new Rol();
                    rol.setNombre(nombre);
                    rolRepository.save(rol);
                }
            }

            // Cuentas de prueba: admin@duoc.cl / Admin123, vendedor@duoc.cl / Vende123, cliente@gmail.com / Cliente1
            if (usuarioRepository.count() == 0) {
                usuarioRepository.save(usuario("111111111", "Admin", "NutriVifs", "admin@duoc.cl", "Admin123", Rol.ADMINISTRADOR));
                usuarioRepository.save(usuario("222222222", "Vendedor", "NutriVifs", "vendedor@duoc.cl", "Vende123", Rol.VENDEDOR));
                usuarioRepository.save(usuario("333333333", "Cliente", "Demo", "cliente@gmail.com", "Cliente1", Rol.CLIENTE));
            }

            if (categoriaProductoRepository.count() == 0) {
                for (String nombre : List.of("Antibióticos", "Antiparasitarios", "Antiinflamatorios", "Dermatología",
                        "Digestivo", "Cardíaco", "Analgésicos", "Vacunas", "Suplementos")) {
                    CategoriaProducto categoria = new CategoriaProducto();
                    categoria.setNombre(nombre);
                    categoriaProductoRepository.save(categoria);
                }
            }

            // Se insertan en orden, así quedan con id 1..22 = códigos ME001..ME022 del frontend
            if (productoRepository.count() == 0) {
                productoRepository.save(producto("Amoxibay 250mg", "Amoxicilina — Blíster 10 comp.",
                        "Perro / Gato", 4200, 45, 20, "Antibióticos", "/img/Amoxibay.png"));
                productoRepository.save(producto("Enrox 50mg", "Enrofloxacino — Blíster 10 comp.",
                        "Perro / Gato", 6800, 30, 20, "Antibióticos", "/img/enrrox.jpg"));
                productoRepository.save(producto("Metrobay 250mg", "Metronidazol — Blíster 10 comp.",
                        "Perro / Gato", 3900, 28, 20, "Antibióticos", "/img/Metrobay.jpg"));
                productoRepository.save(producto("Nexgard", "Afoxolaner — Masticable 1 unid.",
                        "Perro", 9500, 60, 20, "Antiparasitarios", "/img/Nexgard.jpg"));
                productoRepository.save(producto("Bravecto", "Fluralaner — Masticable 1 unid.",
                        "Perro", 18900, 40, 20, "Antiparasitarios", "/img/Bravecto.jpg"));
                productoRepository.save(producto("Revolution Plus", "Selamectina + Sarolaner — Pipeta 1 unid.",
                        "Gato", 14500, 35, 20, "Antiparasitarios", "/img/RevolutionPlus.jpg"));
                productoRepository.save(producto("Drontal Plus", "Praziquantel + Pamoato — Comprimido 1 unid.",
                        "Perro", 3200, 80, 20, "Antiparasitarios", "/img/DrontalPlus.jpg"));
                productoRepository.save(producto("Milbemax Gato", "Milbemicina + Praziq. — Comprimido 2 unid.",
                        "Gato", 6800, 50, 20, "Antiparasitarios", "/img/MilbemaxGato.jpg"));
                productoRepository.save(producto("Meloxicam 1mg", "Meloxicam — Blíster 10 comp.",
                        "Perro / Gato", 4500, 55, 20, "Antiinflamatorios", "/img/Metrobay.jpg"));
                productoRepository.save(producto("Carprofen 50mg", "Carprofeno — Blíster 10 comp.",
                        "Perro", 9800, 30, 20, "Antiinflamatorios", "/img/apoquel.jpg"));
                productoRepository.save(producto("Clorhexidina shampoo", "Clorhexidina 2% — Frasco 250ml",
                        "Perro / Gato", 8900, 25, 20, "Dermatología", "/img/Omeprazol.jpg"));
                productoRepository.save(producto("Malaseb shampoo", "Miconazol + Clorhex. — Frasco 250ml",
                        "Perro / Gato", 12500, 20, 20, "Dermatología", "/img/Tramadol.jpg"));
                productoRepository.save(producto("Apoquel 16mg", "Oclacitinib — Blíster 10 comp.",
                        "Perro", 22000, 18, 20, "Dermatología", "/img/apoquel.jpg"));
                productoRepository.save(producto("Probifor", "Bacillus clausii — Sobre 5ml x10",
                        "Perro / Gato", 5600, 40, 20, "Digestivo", "/img/probifor.jpg"));
                productoRepository.save(producto("Omeprazol 10mg vet", "Omeprazol — Blíster 10 comp.",
                        "Perro / Gato", 3800, 35, 20, "Digestivo", "/img/Omeprazol.jpg"));
                productoRepository.save(producto("Vetmedin 2.5mg", "Pimobendan — Blíster 10 comp.",
                        "Perro", 28000, 15, 20, "Cardíaco", "/img/Vetmedin.jpg"));
                productoRepository.save(producto("Tramadol 50mg vet", "Tramadol — Blíster 10 comp.",
                        "Perro", 5200, 22, 20, "Analgésicos", "/img/Tramadol.jpg"));
                productoRepository.save(producto("Nobivac DHPPi", "Vacuna polivalente — Vial 1 dosis",
                        "Perro", 8500, 48, 20, "Vacunas", "/img/NobivacDHPPi.jpg"));
                productoRepository.save(producto("Nobivac Rabies", "Vacuna antirrábica — Vial 1 dosis",
                        "Perro / Gato", 5800, 60, 20, "Vacunas", "/img/NobivacRabies.jpg"));
                productoRepository.save(producto("Felocell CVR", "Vacuna triple felina — Vial 1 dosis",
                        "Gato", 7200, 36, 20, "Vacunas", "/img/FelocellCVR.jpg"));
                productoRepository.save(producto("Omega vet 3-6-9", "Ácidos grasos omega — Frasco 100ml",
                        "Perro / Gato", 9900, 30, 20, "Suplementos", "/img/Omegavet%203-6-9.jpg"));
                productoRepository.save(producto("Condrovet forte", "Condroitín + Glucos. — Blíster 30 comp.",
                        "Perro", 14500, 25, 20, "Suplementos", "/img/Condrovetforte.jpg"));
            }

            if (categoriaServicioRepository.count() == 0) {
                for (String nombre : List.of("Consultas", "Vacunación", "Cirugías", "Desparasitación", "Exámenes")) {
                    Categoria categoria = new Categoria();
                    categoria.setNombre(nombre);
                    categoriaServicioRepository.save(categoria);
                }
            }

            if (servicioRepository.count() == 0) {
                servicioRepository.save(servicio("Consulta general", "Revisión médica completa para evaluar el estado de salud de tu mascota.",
                        "Perro / Gato", 30, "Atención veterinaria general.", 15000, "Consulta general", "/img/consulta-general.jpg", "Consulta general veterinaria", false, "Consultas"));
                servicioRepository.save(servicio("Consulta de urgencia", "Atención veterinaria para situaciones que requieren atención inmediata.",
                        "Perro / Gato", 30, "Disponible 24/7, sin hora previa.", 25000, "Consulta de urgencia", "/img/consulta-urgencia.jpg", "Consulta de urgencia veterinaria", false, "Consultas"));
                servicioRepository.save(servicio("Control postoperatorio", "Seguimiento médico después de una intervención quirúrgica.",
                        "Perro / Gato", 20, "Control y seguimiento de recuperación.", 10000, "Control postoperatorio", "/img/Control-postoperatorio.jpg", "Control postoperatorio", false, "Consultas"));
                servicioRepository.save(servicio("Consulta ave / conejo", "Atención veterinaria especializada para aves y conejos.",
                        "Ave / Conejo", 30, "Atención para animales pequeños.", 18000, "Consulta ave / conejo", "/img/Consulta-conejo.jpg", "Consulta para aves y conejos", false, "Consultas"));
                servicioRepository.save(servicio("Segunda opinión médica", "Evaluación adicional de un diagnóstico o tratamiento veterinario.",
                        "Todas", 40, "Requiere ficha previa.", 20000, "Segunda opinión médica", "/img/evaluacion.jpg", "Segunda opinión médica", false, "Consultas"));
                servicioRepository.save(servicio("Vacuna antirrábica canina", "Vacunación contra la rabia para perros.",
                        "Perro", 10, "Obligatoria por ley.", 12000, "antirrabica-canina", "/img/Vacuna-antirrábica.png", "Vacuna antirrábica canina", true, "Vacunación"));
                servicioRepository.save(servicio("Vacuna séxtuple canina", "Protección preventiva contra diferentes enfermedades caninas.",
                        "Perro", 10, "Refuerzo anual.", 18000, "sextuple-canina", "/img/Vacuna-séxtuple.jpg", "Vacuna séxtuple canina", true, "Vacunación"));
                servicioRepository.save(servicio("Vacuna bivalente felina", "Protección preventiva para gatos.",
                        "Gato", 10, "Refuerzo anual.", 15000, "bivalente-felina", "/img/Vacuna-bivalente.jpg", "Vacuna bivalente felina", true, "Vacunación"));
                servicioRepository.save(servicio("Vacuna triple felina", "Protección preventiva mediante vacunación para gatos.",
                        "Gato", 10, "Refuerzo anual.", 17000, "triple-felina", "/img/Vacuna-triple.jpg", "Vacuna triple felina", true, "Vacunación"));
                servicioRepository.save(servicio("Vacuna Bordetella canina", "Prevención de enfermedades respiratorias en perros.",
                        "Perro", 10, "Tos de las perreras.", 14000, "bordetella-canina", "/img/Vacuna-Bordetella.jpg", "Vacuna Bordetella canina", true, "Vacunación"));
                servicioRepository.save(servicio("Vacuna antirrábica felina", "Vacunación contra la rabia para gatos.",
                        "Gato", 10, "Protección preventiva.", 12000, "antirrabica-felina", "/img/antirrabica.png", "Vacuna antirrábica felina", true, "Vacunación"));
                servicioRepository.save(servicio("Esterilización hembra canina", "Procedimiento de esterilización para hembras caninas.",
                        "Perra", 90, "Incluye anestesia y hospitalización 24h.", 80000, "Esterilización hembra canina", "/img/Esterilización-hembra.jpg", "Esterilización hembra canina", false, "Cirugías"));
                servicioRepository.save(servicio("Esterilización macho canino", "Procedimiento de esterilización para perros machos.",
                        "Perro", 60, "Incluye anestesia.", 60000, "Esterilización macho canino", "/img/esterilizacion-perro.jpg", "Esterilización macho canino", false, "Cirugías"));
                servicioRepository.save(servicio("Esterilización hembra felina", "Procedimiento de esterilización para gatas.",
                        "Gata", 60, "Incluye anestesia y hospitalización 12h.", 65000, "Esterilización hembra felina", "/img/castracion-gato.jpg", "Esterilización hembra felina", false, "Cirugías"));
                servicioRepository.save(servicio("Esterilización macho felino", "Procedimiento de esterilización para gatos machos.",
                        "Gato", 45, "Incluye anestesia.", 50000, "Esterilización macho felino", "/img/Esterilización-gatoHjpg.jpg", "Esterilización macho felino", false, "Cirugías"));
                servicioRepository.save(servicio("Extirpación de tumor cutáneo", "Procedimiento quirúrgico para retirar tumores cutáneos.",
                        "Perro / Gato", 60, "Precio referencial; varía según tamaño.", 120000, "Extirpación de tumor cutáneo", "/img/extirpacion_cancer.jpg", "Extirpación de tumor cutáneo", false, "Cirugías"));
                servicioRepository.save(servicio("Cesárea de urgencia", "Procedimiento quirúrgico de emergencia para hembras gestantes.",
                        "Perra / Gata", 120, "Atención de urgencia.", 180000, "Cesárea de urgencia", "/img/cesarea.jpg", "Cesárea de urgencia", false, "Cirugías"));
                servicioRepository.save(servicio("Desparasitación interna pequeños", "Tratamiento antiparasitario para perros pequeños de menos de 10 kg.",
                        "Perro", 5, "Menos de 10 kg.", 8000, "Desparasitación interna pequeños", "/img/DrontalPlus.jpg", "Desparasitación interna pequeños", false, "Desparasitación"));
                servicioRepository.save(servicio("Desparasitación interna medianos", "Tratamiento antiparasitario para perros de peso mediano.",
                        "Perro", 5, "Entre 10 y 25 kg.", 9500, "Desparasitación interna medianos", "/img/DrontalPlus.jpg", "Desparasitación interna medianos", false, "Desparasitación"));
                servicioRepository.save(servicio("Desparasitación interna grandes", "Tratamiento antiparasitario para perros de gran tamaño.",
                        "Perro", 5, "Más de 25 kg.", 11000, "Desparasitación interna grandes", "/img/DrontalPlus.jpg", "Desparasitación interna grandes", false, "Desparasitación"));
                servicioRepository.save(servicio("Desparasitación interna felina", "Tratamiento antiparasitario interno para gatos.",
                        "Gato", 5, "Aplicación según peso.", 8000, "Desparasitación interna felina", "/img/DrontalPlus.jpg", "Desparasitación interna felina", false, "Desparasitación"));
                servicioRepository.save(servicio("Antiparasitario externo", "Aplicación de pipeta para el control de parásitos externos.",
                        "Perro / Gato", 5, "Incluye aplicación.", 7500, "Antiparasitario externo (pipeta)", "/img/Bravecto.jpg", "Antiparasitario externo", false, "Desparasitación"));
                servicioRepository.save(servicio("Hemograma completo", "Análisis de sangre para evaluar el estado general de salud.",
                        "Perro / Gato", 30, "Resultado en 24-48 h.", 22000, "Hemograma completo", "/img/hemograma.jpg", "Hemograma completo", false, "Exámenes"));
                servicioRepository.save(servicio("Perfil bioquímico completo", "Evaluación de parámetros bioquímicos importantes para la salud.",
                        "Perro / Gato", 30, "Resultado en 24-48 h.", 35000, "Perfil bioquímico completo", "/img/perfill-bioquimico.jpg", "Perfil bioquímico completo", false, "Exámenes"));
                servicioRepository.save(servicio("Radiografía", "Estudio radiográfico para apoyar el diagnóstico veterinario.",
                        "Perro / Gato", 20, "1 proyección.", 28000, "Radiografía (1 proyección)", "/img/Radiografía.jpg", "Radiografía", false, "Exámenes"));
                servicioRepository.save(servicio("Ecografía abdominal", "Evaluación mediante imagen de órganos y estructuras abdominales.",
                        "Perro / Gato", 30, "Examen abdominal.", 45000, "Ecografía abdominal", "/img/ecografia.jpg", "Ecografía abdominal", false, "Exámenes"));
                servicioRepository.save(servicio("Test de leishmaniasis", "Prueba diagnóstica para detectar leishmaniasis en perros.",
                        "Perro", 15, "Prueba rápida.", 18000, "Test de leishmaniasis", "/img/leishmaniasis.jpg", "Test de leishmaniasis", false, "Exámenes"));
            }
        };
    }

    private Usuario usuario(String run, String nombre, String apellido, String mail, String clave, String rol) {
        Usuario u = new Usuario();
        u.setRun(run);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setMail(mail);
        u.setPassword(passwordEncoder.encode(clave));
        u.setRol(rolRepository.findByNombreIgnoreCase(rol).orElseThrow());
        u.setDireccion("Av. San Marcos 123");
        u.setRegion("Libertador General Bernardo O'Higgins");
        u.setComuna("Rancagua");
        return u;
    }

    private Producto producto(String nombre, String descripcion, String especies, int precio, int stock,
                              int stockCritico, String categoria, String imagen) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setEspecies(especies);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setStockCritico(stockCritico);
        p.setImage(imagen);
        p.setCategoria(categoriaProductoRepository.findByNombre(categoria).orElseThrow());
        return p;
    }

    private Servicio servicio(String nombre, String descripcion, String especie, int duracion, String observacion,
                              int precio, String consulta, String imagen, String alt, boolean enlace, String categoria) {
        Servicio s = new Servicio();
        s.setNombre(nombre);
        s.setDescripcion(descripcion);
        s.setEspecie(especie);
        s.setDuracion(duracion);
        s.setObservacion(observacion);
        s.setPrecio(precio);
        s.setConsulta(consulta);
        s.setImage(imagen);
        s.setAlt(alt);
        s.setEnlace(enlace);
        s.setCategoria(categoriaServicioRepository.findByNombre(categoria).orElseThrow());
        return s;
    }

}
