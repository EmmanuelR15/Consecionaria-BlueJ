package negocio;

import diseno.*;

/**
 * Representa un vehículo genérico siguiendo el patrón JavaBean.
 */
public abstract class Vehiculo implements Persistible {

    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private boolean esUsado;
    private boolean mantenimientoRealizado;

    protected Vehiculo() {
        // Constructor por defecto requerido por JavaBean.
    }

    protected Vehiculo(String marca, String modelo, int anio, String color, boolean esUsado,
            boolean mantenimientoRealizado) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.esUsado = esUsado;
        this.mantenimientoRealizado = mantenimientoRealizado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEsUsado() {
        return esUsado;
    }

    public void setEsUsado(boolean esUsado) {
        this.esUsado = esUsado;
    }

    public boolean isMantenimientoRealizado() {
        return mantenimientoRealizado;
    }

    public void setMantenimientoRealizado(boolean mantenimientoRealizado) {
        this.mantenimientoRealizado = mantenimientoRealizado;
    }

    @Override
    public void guardar() {
        // Simulación de persistencia
        System.out.println("Guardando vehículo: " + this);
    }

    @Override
    public void cargar() {
        // Simulación de carga
        System.out.println("Cargando vehículo: " + this);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", color='" + color + '\'' +
                ", esUsado=" + esUsado +
                ", mantenimientoRealizado=" + mantenimientoRealizado +
                '}';
    }
}

