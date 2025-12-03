package negocio;

import diseno.*;

/**
 * Gestiona la cola de vehículos que necesitan mantenimiento.
 */
public class Taller {

    private final Cola<Vehiculo> colaMantenimiento;

    public Taller() {
        this.colaMantenimiento = new Cola<>();
    }

    public void agregarVehiculoACola(Vehiculo vehiculo) throws VehiculoException {
        if (vehiculo == null) {
            throw new VehiculoException("El vehículo no puede ser nulo");
        }
        if (!vehiculo.isEsUsado()) {
            throw new VehiculoException("Solo se aceptan vehículos usados en mantenimiento");
        }
        if (vehiculo.isMantenimientoRealizado()) {
            throw new VehiculoException("El vehículo ya tiene mantenimiento realizado");
        }
        colaMantenimiento.encolar(vehiculo);
    }

    public Vehiculo atenderSiguienteVehiculo() throws VehiculoException {
        if (colaMantenimiento.estaVacia()) {
            throw new VehiculoException("No hay vehículos en la cola de mantenimiento");
        }
        Vehiculo vehiculo = colaMantenimiento.desencolar();
        if (vehiculo instanceof Mantenible) {
            ((Mantenible) vehiculo).realizarMantenimiento();
        } else {
            throw new VehiculoException("El vehículo no es mantenible");
        }
        return vehiculo;
    }

    public int obtenerCantidadEnEspera() {
        return colaMantenimiento.tamanio();
    }

    public String verColaCompleta() {
        StringBuilder builder = new StringBuilder("Cola de mantenimiento:\n");
        builder.append(colaMantenimiento.toString());
        return builder.toString();
    }
}

