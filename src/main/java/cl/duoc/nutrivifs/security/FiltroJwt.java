package cl.duoc.nutrivifs.security;

import cl.duoc.nutrivifs.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// Lee el header "Authorization: Bearer <token>" de cada petición y, si el token es válido,
// deja al usuario autenticado con su rol (ROLE_ADMINISTRADOR, ROLE_VENDEDOR o ROLE_CLIENTE)
@Component
public class FiltroJwt extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String cabecera = request.getHeader("Authorization");
        if (cabecera != null && cabecera.startsWith("Bearer ")) {
            String token = cabecera.substring(7);
            if (jwtService.esValido(token)) {
                usuarioRepository.findByMail(jwtService.obtenerMail(token))
                        .filter(usuario -> !usuario.isBloqueado() && usuario.getRol() != null)
                        .ifPresent(usuario -> {
                            String rol = "ROLE_" + usuario.getRol().getNombre().toUpperCase();
                            var autenticacion = new UsernamePasswordAuthenticationToken(
                                    usuario.getMail(), null, List.of(new SimpleGrantedAuthority(rol)));
                            SecurityContextHolder.getContext().setAuthentication(autenticacion);
                        });
            }
        }
        chain.doFilter(request, response);   // sigue hacia el controlador
    }

}
