import controller.Concesionario;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Concesionario concesionario = new Concesionario();

        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Añadir un nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consulta coche por ID");
            System.out.println("4. Modificar potencia de un coche");
            System.out.println("5. Listado de coches");
            System.out.println("6. Gestion de pasajeros");
            System.out.println("7. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    concesionario.addCoche();
                    break;
                case 2:
                    concesionario.borrarCoche();
                    break;
                case 3:
                    concesionario.buscarCocheId();
                    break;
                case 4:
                    concesionario.actualizarCoche();
                    break;
                case 5:
                    concesionario.sacarListado();
                    break;
                case 6:
                    menuPasajeros();
                    break;
            }
        } while (opcion != 7);
    }

    private static void menuPasajeros() {
        int opcion2 = 0;
        Concesionario concesionario = new Concesionario();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1. Añadir nuevo pasajero");
            System.out.println("2. Borrar pasajero por id");
            System.out.println("3. Consulta pasajero por id");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar todos los pasajeros de un coche");
            System.out.println("8. Salir");
            opcion2 = sc.nextInt();
            switch (opcion2) {
                case 1:
                    concesionario.addPasajero();
                    break;
                case 2:
                    concesionario.borrarPasajero();
                    break;
                case 3:
                    concesionario.buscarPasajeroId();
                    break;
                case 4:
                    concesionario.listarPasajeros();
                    break;
                case 5:
                    concesionario.sacarListado();
                    concesionario.asignarPasajeroCoche();
                    break;
                case 6:
                    concesionario.eliminarPasajeroCoche();
                    break;
                case 7:
                    concesionario.listadoPasajerosPorCoche();

            }
        } while (opcion2 != 8);
    }
}
