package negocio;

public final class Lavadero {

    private Lavadero() {
        // Utilidad estática, no instanciable.
    }

    public static void lavar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            System.out.println("No hay vehículo para lavar.");
            return;
        }
        System.out.println("Lavado completado para: " + vehiculo);
    }
}

