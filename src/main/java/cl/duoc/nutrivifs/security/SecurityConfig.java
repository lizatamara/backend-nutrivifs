package cl.duoc.nutrivifs.security;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// Permisos de cada endpoint según el rol (Anexo 1), CORS para React y cifrado de contraseñas
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FiltroJwt filtroJwt;

    @Bean
    public SecurityFilterChain seguridad(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())                 // API con token: sin cookies ni formularios
                .cors(Customizer.withDefaults())              // usa el bean de CORS de abajo
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión"))   // 401
                        .accessDeniedHandler((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permiso")))        // 403
                .authorizeHttpRequests(auth -> auth
                        // Público: Swagger, login/registro y ver la tienda (productos, servicios y sus categorías)
                        .requestMatchers("/error", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/v1/auth/login", "/api/v1/auth/registro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/productos/**", "/api/v1/servicios/**", "/api/v1/resenas/producto/**", "/uploads/**").permitAll()
                        // Citas y mensajes: cualquier visitante puede enviar una solicitud o mensaje.
                        .requestMatchers(HttpMethod.POST, "/api/v1/citas", "/api/v1/mensajes").permitAll()
                        // Los clientes consultan sus citas; administración y ventas ven la bandeja completa.
                        .requestMatchers(HttpMethod.GET, "/api/v1/citas/mias").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/citas").hasAnyRole("ADMINISTRADOR", "VENDEDOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/citas/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mensajes").hasAnyRole("ADMINISTRADOR", "VENDEDOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/mensajes/**").hasRole("ADMINISTRADOR")
                        // Las reseñas se leen públicamente; escribir requiere sesión y borrar se valida por autor/administrador.
                        .requestMatchers(HttpMethod.POST, "/api/v1/resenas").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/resenas/**").authenticated()
                        // Subir imágenes solo desde el panel administrador.
                        .requestMatchers(HttpMethod.POST, "/api/v1/archivos/**").hasRole("ADMINISTRADOR")
                        // Reportes del dashboard: solo Administrador.
                        .requestMatchers(HttpMethod.GET, "/api/v1/reportes/**").hasRole("ADMINISTRADOR")
                        // Cualquier usuario con sesión: renovar token, ver/editar su perfil, comprar y ver sus compras
                        .requestMatchers("/api/v1/auth/refresh", "/api/v1/auth/me").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/usuarios/me").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/boletas").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/boletas/mias").authenticated()
                        // Vendedor: ver la lista de órdenes y su detalle (y los productos, que ya son públicos)
                        .requestMatchers(HttpMethod.GET, "/api/v1/boletas", "/api/v1/boletas/**")
                        .hasAnyRole("ADMINISTRADOR", "VENDEDOR")
                        // Todo lo demás (crear, editar, eliminar, usuarios, roles, estados): solo Administrador
                        .anyRequest().hasRole("ADMINISTRADOR"))
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Permite que React (localhost:5173) llame a la API (localhost:8080)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}