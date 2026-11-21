package cl.duoc.nutrivifs.archivo.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/** Recibe imágenes del panel administrador y las guarda en la carpeta uploads. */
@RestController
@RequestMapping("/api/v1/archivos")
public class ArchivoController {
    private static final Path CARPETA = Path.of("uploads").toAbsolutePath().normalize();
    private static final Set<String> EXTENSIONES = Set.of("jpg", "jpeg", "png", "webp");

    @PostMapping(value = "/imagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> subirImagen(@NotNull @RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecciona una imagen.");
        }
        if (archivo.getSize() > 5 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La imagen no puede superar 5 MB.");
        }

        String original = StringUtils.cleanPath(archivo.getOriginalFilename() == null ? "imagen" : archivo.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(original);
        if (extension == null || !EXTENSIONES.contains(extension.toLowerCase(Locale.ROOT))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato permitido: JPG, JPEG, PNG o WEBP.");
        }
        String tipo = archivo.getContentType();
        if (tipo == null || !Set.of("image/jpeg", "image/png", "image/webp").contains(tipo.toLowerCase(Locale.ROOT))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo debe ser una imagen válida.");
        }

        String nombre = UUID.randomUUID() + "." + extension.toLowerCase(Locale.ROOT);
        try {
            Files.createDirectories(CARPETA);
            Files.copy(archivo.getInputStream(), CARPETA.resolve(nombre));
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo guardar la imagen.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("nombre", nombre, "url", "/uploads/" + nombre));
    }
}
