package diseno;

/**
 * Define operaciones de mantenimiento para los vehículos.
 */
public interface Mantenible {

    /**
     * Ejecuta las tareas de mantenimiento necesarias sobre el vehículo.
     */
    void realizarMantenimiento();

    /**
     * Determina si el vehículo requiere mantenimiento.
     *
     * @return true si el mantenimiento es requerido; false en caso contrario.
     */
    boolean necesitaMantenimiento();
}

