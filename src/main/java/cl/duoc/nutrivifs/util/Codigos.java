package cl.duoc.nutrivifs.util;

// Convierte el id numérico de la BD en el código que usa el frontend y al revés:
// producto 5 <-> "ME005", categoría 3 <-> "CAT03", servicio 12 <-> "SE012"
public final class Codigos {

    private Codigos() {
    }

    public static String formatear(String prefijo, int digitos, Long id) {
        return prefijo + String.format("%0" + digitos + "d", id);
    }

    // Acepta "ME005" o "5"
    public static Long aId(String codigo) {
        String numeros = codigo == null ? "" : codigo.replaceAll("\\D", "");
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }
        return Long.parseLong(numeros);
    }
}
