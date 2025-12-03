package diseno;

/**
 * Excepción específica para operaciones relacionadas con vehículos.
 */
public class VehiculoException extends Exception {

    public VehiculoException() {
        super();
    }

    public VehiculoException(String message) {
        super(message);
    }

    public VehiculoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehiculoException(Throwable cause) {
        super(cause);
    }
}

