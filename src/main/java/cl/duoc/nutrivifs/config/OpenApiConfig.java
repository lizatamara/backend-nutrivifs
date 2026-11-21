package cl.duoc.nutrivifs.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

// Título de Swagger y botón "Authorize" para probar los endpoints con el token JWT
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "API Clínica NutriVifs", version = "v1",
                description = "Productos, servicios, usuarios, autenticación y boletas de la tienda"),
        security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {
}
