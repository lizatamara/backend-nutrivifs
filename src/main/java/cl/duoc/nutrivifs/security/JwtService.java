package cl.duoc.nutrivifs.security;

import cl.duoc.nutrivifs.usuario.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Genera y verifica los tokens JWT (firmados con HMAC-SHA y la clave de application.properties)
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration-ms:3600000}")   // 1 hora por defecto
    private long duracionMs;

    private SecretKey clave(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secreto));
    }

    public String generarToken(Usuario usuario){
        Date ahora = new Date();
        return Jwts.builder()
                .subject(usuario.getMail())                          // quién es
                .claim("rol", usuario.getRol().getNombre())          // su rol
                .issuedAt(ahora)
                .expiration(new Date(ahora.getTime() + duracionMs))  // cuándo vence
                .signWith(clave())                                    // firma
                .compact();
    }

    public String obtenerMail(String token){
        return leer(token).getSubject();
    }

    public boolean esValido(String token){
        try {
            leer(token);   // falla si la firma no coincide o si el token venció
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims leer(String token){
        return Jwts.parser().verifyWith(clave()).build().parseSignedClaims(token).getPayload();
    }

}
