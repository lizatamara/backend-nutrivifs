package cl.duoc.nutrivifs.exception;

// Correo o contraseña incorrectos, o cuenta bloqueada (responde 401)
public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }
}
