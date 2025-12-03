package negocio;

import diseno.*;

/**
 * Representa una camioneta o pickup.
 */
public class Camioneta extends VehiculoTerrestre implements Mantenible {

    private double capacidadCarga;

    public Camioneta() {
        // Constructor por defecto requerido por JavaBean.
    }

    public Camioneta(String marca, String modelo, int anio, String color, boolean esUsado,
            boolean mantenimientoRealizado, TipoCarroceria tipoCarroceria, double capacidadCarga) {
        super(marca, modelo, anio, color, esUsado, mantenimientoRealizado, tipoCarroceria);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
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
        return "Camioneta{" +
                "capacidadCarga=" + capacidadCarga +
                ", datos=" + super.toString() +
                '}';
    }
}

