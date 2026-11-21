package cl.duoc.nutrivifs.exception;

// Se lanza cuando no existe el registro pedido (responde 404)
public class NoEncontradoException extends RuntimeException {
    public NoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
