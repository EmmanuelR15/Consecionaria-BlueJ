package negocio;

/**
 * Especialización de vehículo para aquellos que poseen carrocería.
 */
public abstract class VehiculoTerrestre extends Vehiculo {

    private TipoCarroceria tipoCarroceria;

    protected VehiculoTerrestre() {
        // Constructor por defecto requerido por JavaBean.
    }

    protected VehiculoTerrestre(String marca, String modelo, int anio, String color, boolean esUsado,
            boolean mantenimientoRealizado, TipoCarroceria tipoCarroceria) {
        super(marca, modelo, anio, color, esUsado, mantenimientoRealizado);
        this.tipoCarroceria = tipoCarroceria;
    }

    public TipoCarroceria getTipoCarroceria() {
        return tipoCarroceria;
    }

    public void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
        this.tipoCarroceria = tipoCarroceria;
    }

    @Override
    public String toString() {
        return "VehiculoTerrestre{" +
                "tipoCarroceria=" + tipoCarroceria +
                ", datosVehiculo=" + super.toString() +
                '}';
    }
}

