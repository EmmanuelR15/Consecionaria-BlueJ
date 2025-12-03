package negocio;

import diseno.*;

/**
 * Representa una motocicleta y sus características principales.
 */
public class Motocicleta extends Vehiculo implements Mantenible {

    private TipoMotocicleta tipoMotocicleta;

    public Motocicleta() {
        // Constructor por defecto requerido por JavaBean.
    }

    public Motocicleta(String marca, String modelo, int anio, String color, boolean esUsado,
            boolean mantenimientoRealizado, TipoMotocicleta tipoMotocicleta) {
        super(marca, modelo, anio, color, esUsado, mantenimientoRealizado);
        this.tipoMotocicleta = tipoMotocicleta;
    }

    public TipoMotocicleta getTipoMotocicleta() {
        return tipoMotocicleta;
    }

    public void setTipoMotocicleta(TipoMotocicleta tipoMotocicleta) {
        this.tipoMotocicleta = tipoMotocicleta;
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
        return "Motocicleta{" +
                "tipoMotocicleta=" + tipoMotocicleta +
                ", datos=" + super.toString() +
                '}';
    }
}

