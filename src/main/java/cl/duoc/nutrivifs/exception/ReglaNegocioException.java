package cl.duoc.nutrivifs.exception;

// Se lanza cuando se rompe una regla del negocio: sin stock, cupón inválido... (responde 400)
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
