package diseno;

/**
 * Define operaciones básicas de persistencia.
 */
public interface Persistible {

    /**
     * Persiste el estado actual.
     */
    void guardar();

    /**
     * Carga el estado persistido.
     */
    void cargar();
}

