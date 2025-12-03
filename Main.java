import java.util.Scanner;

import diseno.*;
import negocio.*;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Concesionaria CONCESIONARIA = new Concesionaria();

    public static void main(String[] args) {
        while (true) {
            limpiarPantalla();
            System.out.println("=== CONCESIONARIA DE VEHÍCULOS ===");
            System.out.println("1. Agregar vehículo al inventario");
            System.out.println("2. Listar inventario completo");
            System.out.println("3. Buscar vehículo");
            System.out.println("4. Modificar vehículo");
            System.out.println("5. Eliminar vehículo");
            System.out.println("6. Enviar vehículo usado a taller");
            System.out.println("7. Atender vehículo en taller");
            System.out.println("8. Ver cola del taller");
            System.out.println("9. Lavar vehículo");
            System.out.println("10. Guardar inventario");
            System.out.println("11. Cargar inventario");
            System.out.println("0. Salir");
            int opcion = leerEnteroEnRango("Seleccione una opción: ", 0, 11);

            try {
                switch (opcion) {
                    case 1:
                        agregarVehiculo();
                        break;
                    case 2:
                        listarInventario();
                        break;
                    case 3:
                        buscarVehiculo();
                        break;
                    case 4:
                        modificarVehiculo();
                        break;
                    case 5:
                        eliminarVehiculo();
                        break;
                    case 6:
                        enviarVehiculoATaller();
                        break;
                    case 7:
                        atenderVehiculoTaller();
                        break;
                    case 8:
                        verColaTaller();
                        break;
                    case 9:
                        lavarVehiculo();
                        break;
                    case 10:
                        guardarInventario();
                        break;
                    case 11:
                        cargarInventario();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (VehiculoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

            pausar();
        }
    }

    private static void agregarVehiculo() throws VehiculoException {
        limpiarPantalla();
        System.out.println("=== ALTA DE VEHÍCULO ===");
        System.out.println("1. Automóvil");
        System.out.println("2. Camioneta");
        System.out.println("3. Motocicleta");
        int tipo = leerEnteroEnRango("Seleccione tipo: ", 1, 3);

        Vehiculo vehiculo;
        switch (tipo) {
            case 1:
                vehiculo = crearAutomovil();
                break;
            case 2:
                vehiculo = crearCamioneta();
                break;
            case 3:
                vehiculo = crearMotocicleta();
                break;
            default:
                throw new VehiculoException("Tipo de vehículo inválido.");
        }
        CONCESIONARIA.agregarVehiculo(vehiculo);
        System.out.println("Vehículo agregado correctamente.");
    }

    private static void listarInventario() {
        limpiarPantalla();
        System.out.println(CONCESIONARIA.listarInventario());
    }

    private static void buscarVehiculo() {
        limpiarPantalla();
        Vehiculo vehiculo = solicitarVehiculoExistente();
        if (vehiculo != null) {
            System.out.println("Vehículo encontrado:\n" + vehiculo);
        }
    }

    private static void modificarVehiculo() throws VehiculoException {
        limpiarPantalla();
        Vehiculo vehiculo = solicitarVehiculoExistente();
        if (vehiculo == null) {
            return;
        }
        Vehiculo nuevoVehiculo;
        if (vehiculo instanceof Automovil) {
            nuevoVehiculo = crearAutomovil();
        } else if (vehiculo instanceof Camioneta) {
            nuevoVehiculo = crearCamioneta();
        } else if (vehiculo instanceof Motocicleta) {
            nuevoVehiculo = crearMotocicleta();
        } else {
            System.out.println("Tipo de vehículo no soportado.");
            return;
        }
        CONCESIONARIA.modificarVehiculo(vehiculo, nuevoVehiculo);
        System.out.println("Vehículo modificado exitosamente.");
    }

    private static void eliminarVehiculo() throws VehiculoException {
        limpiarPantalla();
        Vehiculo vehiculo = solicitarVehiculoExistente();
        if (vehiculo == null) {
            return;
        }
        CONCESIONARIA.eliminarVehiculo(vehiculo);
        System.out.println("Vehículo eliminado del inventario.");
    }

    private static void enviarVehiculoATaller() throws VehiculoException {
        limpiarPantalla();
        Vehiculo vehiculo = solicitarVehiculoExistente();
        if (vehiculo == null) {
            return;
        }
        CONCESIONARIA.enviarVehiculoATaller(vehiculo);
        System.out.println("Vehículo enviado al taller.");
    }

    private static void atenderVehiculoTaller() throws VehiculoException {
        limpiarPantalla();
        Vehiculo atendido = CONCESIONARIA.getTaller().atenderSiguienteVehiculo();
        System.out.println("Vehículo atendido en taller:\n" + atendido);
        Lavadero.lavar(atendido);
    }

    private static void verColaTaller() {
        limpiarPantalla();
        System.out.println(CONCESIONARIA.getTaller().verColaCompleta());
    }

    private static void lavarVehiculo() {
        limpiarPantalla();
        Vehiculo vehiculo = solicitarVehiculoExistente();
        if (vehiculo == null) {
            return;
        }
        Lavadero.lavar(vehiculo);
    }

    private static void guardarInventario() throws VehiculoException {
        limpiarPantalla();
        CONCESIONARIA.guardarInventario();
        System.out.println("Inventario guardado en archivo.");
    }

    private static void cargarInventario() throws VehiculoException {
        limpiarPantalla();
        CONCESIONARIA.cargarInventario();
        System.out.println("Inventario cargado desde archivo.");
    }

    private static Vehiculo crearAutomovil() {
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerAnio();
        String color = leerTexto("Color: ");
        boolean esUsado = leerBoolean("¿Es usado? (s/n): ");
        boolean mantenimiento = leerBoolean("¿Tiene mantenimiento realizado? (s/n): ");
        TipoCarroceria carroceria = seleccionarTipoCarroceria();
        return new Automovil(marca, modelo, anio, color, esUsado, mantenimiento, carroceria);
    }

    private static Vehiculo crearCamioneta() {
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerAnio();
        String color = leerTexto("Color: ");
        boolean esUsado = leerBoolean("¿Es usada? (s/n): ");
        boolean mantenimiento = leerBoolean("¿Tiene mantenimiento realizado? (s/n): ");
        TipoCarroceria carroceria = seleccionarTipoCarroceria();
        double capacidad = leerDouble("Capacidad de carga (kg): ");
        return new Camioneta(marca, modelo, anio, color, esUsado, mantenimiento, carroceria, capacidad);
    }

    private static Vehiculo crearMotocicleta() {
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerAnio();
        String color = leerTexto("Color: ");
        boolean esUsado = leerBoolean("¿Es usada? (s/n): ");
        boolean mantenimiento = leerBoolean("¿Tiene mantenimiento realizado? (s/n): ");
        TipoMotocicleta tipo = seleccionarTipoMotocicleta();
        return new Motocicleta(marca, modelo, anio, color, esUsado, mantenimiento, tipo);
    }

    private static Vehiculo solicitarVehiculoExistente() {
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerAnio();
        Vehiculo vehiculo = CONCESIONARIA.buscarVehiculo(marca, modelo, anio);
        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado en el inventario.");
        }
        return vehiculo;
    }

    private static TipoCarroceria seleccionarTipoCarroceria() {
        System.out.println("Seleccione tipo de carrocería:");
        TipoCarroceria[] tipos = TipoCarroceria.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i]);
        }
        int opcion = leerEnteroEnRango("Opción: ", 1, tipos.length);
        return tipos[opcion - 1];
    }

    private static TipoMotocicleta seleccionarTipoMotocicleta() {
        System.out.println("Seleccione tipo de motocicleta:");
        TipoMotocicleta[] tipos = TipoMotocicleta.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i]);
        }
        int opcion = leerEnteroEnRango("Opción: ", 1, tipos.length);
        return tipos[opcion - 1];
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SCANNER.nextLine().trim();
    }

    private static boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = SCANNER.nextLine().trim().toLowerCase();
            if ("s".equals(respuesta)) {
                return true;
            } else if ("n".equals(respuesta)) {
                return false;
            }
            System.out.println("Entrada inválida. Ingrese 's' o 'n'.");
        }
    }

    private static int leerAnio() {
        return leerEnteroEnRango("Año (1900-2025): ", 1900, 2025);
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (SCANNER.hasNextDouble()) {
                double valor = SCANNER.nextDouble();
                SCANNER.nextLine(); // Limpiar
                return valor;
            } else {
                System.out.println("Entrada inválida, ingrese un número decimal.");
                SCANNER.nextLine();
            }
        }
    }

    private static int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            if (SCANNER.hasNextInt()) {
                int valor = SCANNER.nextInt();
                SCANNER.nextLine(); // Limpiar salto
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Valor fuera de rango (" + minimo + "-" + maximo + ").");
            } else {
                System.out.println("Entrada inválida, ingrese un número entero.");
                SCANNER.nextLine();
            }
        }
    }

    private static void limpiarPantalla() {
        System.out.println("\n\n");
    }

    private static void pausar() {
        System.out.println("Presione Enter para continuar...");
        SCANNER.nextLine();
    }
}

