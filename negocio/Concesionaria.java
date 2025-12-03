package negocio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import diseno.*;

/**
 * Controla el inventario de vehículos, así como su persistencia básica.
 */
public class Concesionaria {

    private static final String ARCHIVO_INVENTARIO = "vehiculos.txt";
    private final ArrayList<Vehiculo> inventario;
    private final Taller taller;

    public Concesionaria() {
        this.inventario = new ArrayList<>();
        this.taller = new Taller();
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws VehiculoException {
        if (vehiculo == null) {
            throw new VehiculoException("No se puede agregar un vehículo nulo");
        }
        if (buscarVehiculo(vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getAnio()) != null) {
            throw new VehiculoException("El vehículo ya existe en el inventario");
        }
        inventario.add(vehiculo);
    }

    public Vehiculo buscarVehiculo(String marca, String modelo, int anio) {
        return inventario.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca)
                        && v.getModelo().equalsIgnoreCase(modelo)
                        && v.getAnio() == anio)
                .findFirst()
                .orElse(null);
    }

    public void modificarVehiculo(Vehiculo antiguo, Vehiculo nuevo) throws VehiculoException {
        int indice = inventario.indexOf(antiguo);
        if (indice == -1) {
            throw new VehiculoException("El vehículo a modificar no existe");
        }
        inventario.set(indice, nuevo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) throws VehiculoException {
        if (!inventario.remove(vehiculo)) {
            throw new VehiculoException("El vehículo no se encuentra en el inventario");
        }
    }

    public String listarInventario() {
        StringBuilder builder = new StringBuilder("Inventario:\n");
        for (Vehiculo vehiculo : inventario) {
            builder.append(vehiculo).append('\n');
        }
        return builder.toString();
    }

    public void enviarVehiculoATaller(Vehiculo vehiculo) throws VehiculoException {
        if (vehiculo == null) {
            throw new VehiculoException("No se puede enviar un vehículo nulo al taller");
        }
        if (!inventario.contains(vehiculo)) {
            throw new VehiculoException("El vehículo no pertenece al inventario");
        }
        taller.agregarVehiculoACola(vehiculo);
    }

    public void guardarInventario() throws VehiculoException {
        Path ruta = Paths.get(ARCHIVO_INVENTARIO);
        List<String> lineas = new ArrayList<>();
        for (Vehiculo vehiculo : inventario) {
            lineas.add(serializarVehiculo(vehiculo));
        }
        try {
            Files.write(ruta, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new VehiculoException("No se pudo guardar el inventario", e);
        }
    }

    public void cargarInventario() throws VehiculoException {
        Path ruta = Paths.get(ARCHIVO_INVENTARIO);
        inventario.clear();
        if (!Files.exists(ruta)) {
            return;
        }
        try {
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                Vehiculo vehiculo = deserializarVehiculo(linea);
                if (vehiculo != null) {
                    inventario.add(vehiculo);
                }
            }
        } catch (IOException e) {
            throw new VehiculoException("No se pudo cargar el inventario", e);
        }
    }

    private String serializarVehiculo(Vehiculo vehiculo) {
        StringBuilder builder = new StringBuilder();
        builder.append(vehiculo.getClass().getSimpleName()).append(';')
                .append(vehiculo.getMarca()).append(';')
                .append(vehiculo.getModelo()).append(';')
                .append(vehiculo.getAnio()).append(';')
                .append(vehiculo.getColor()).append(';')
                .append(vehiculo.isEsUsado()).append(';')
                .append(vehiculo.isMantenimientoRealizado());

        if (vehiculo instanceof VehiculoTerrestre) {
            builder.append(';').append(((VehiculoTerrestre) vehiculo).getTipoCarroceria());
        } else {
            builder.append(';');
        }
        if (vehiculo instanceof Camioneta) {
            builder.append(';').append(((Camioneta) vehiculo).getCapacidadCarga());
        } else {
            builder.append(';');
        }
        if (vehiculo instanceof Motocicleta) {
            builder.append(';').append(((Motocicleta) vehiculo).getTipoMotocicleta());
        } else {
            builder.append(';');
        }
        return builder.toString();
    }

    private Vehiculo deserializarVehiculo(String linea) throws VehiculoException {
        String[] partes = linea.split(";");
        if (partes.length < 8) {
            throw new VehiculoException("Línea inválida en inventario: " + linea);
        }
        String tipo = partes[0];
        String marca = partes[1];
        String modelo = partes[2];
        int anio = Integer.parseInt(partes[3]);
        String color = partes[4];
        boolean esUsado = Boolean.parseBoolean(partes[5]);
        boolean mantenimiento = Boolean.parseBoolean(partes[6]);
        String carroceriaStr = partes[7].isEmpty() ? null : partes[7];
        String capacidadStr = partes.length > 8 ? partes[8] : "";
        String tipoMotoStr = partes.length > 9 ? partes[9] : "";

        switch (tipo) {
            case "Automovil":
                return new Automovil(marca, modelo, anio, color, esUsado, mantenimiento,
                        carroceriaStr != null ? TipoCarroceria.valueOf(carroceriaStr) : null);
            case "Camioneta":
                double capacidad = capacidadStr.isEmpty() ? 0 : Double.parseDouble(capacidadStr);
                return new Camioneta(marca, modelo, anio, color, esUsado, mantenimiento,
                        carroceriaStr != null ? TipoCarroceria.valueOf(carroceriaStr) : null, capacidad);
            case "Motocicleta":
                TipoMotocicleta tipoMoto = tipoMotoStr.isEmpty() ? null : TipoMotocicleta.valueOf(tipoMotoStr);
                return new Motocicleta(marca, modelo, anio, color, esUsado, mantenimiento, tipoMoto);
            default:
                // Tipo genérico fallback
                return new Automovil(marca, modelo, anio, color, esUsado, mantenimiento, null);
        }
    }

    public ArrayList<Vehiculo> getInventario() {
        return inventario;
    }

    public Taller getTaller() {
        return taller;
    }
}

