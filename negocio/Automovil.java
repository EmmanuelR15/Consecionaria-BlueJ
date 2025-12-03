package negocio;

import diseno.*;

/**
 * Representa un automóvil convencional.
 */
public class Automovil extends VehiculoTerrestre implements Mantenible {

    public Automovil() {
        // Constructor por defecto requerido por JavaBean.
    }

    public Automovil(String marca, String modelo, int anio, String color, boolean esUsado,
            boolean mantenimientoRealizado, TipoCarroceria tipoCarroceria) {
        super(marca, modelo, anio, color, esUsado, mantenimientoRealizado, tipoCarroceria);
    }

    @Override
    public void realizarMantenimiento() {
        setMantenimientoRealizado(true);
    }

    @Override
    public boolean necesitaMantenimiento() {
        return !isMantenimientoRealizado();
    }

    @Override
    public String toString() {
        return "Automovil{" + "datos=" + super.toString() + '}';
    }
}

