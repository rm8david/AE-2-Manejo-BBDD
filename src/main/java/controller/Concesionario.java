package controller;

import dao.CochesDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {
    private CochesDAO cochesDAO;
    private PasajeroDAO pasajeroDAO;
    Scanner sc = new Scanner(System.in);
    Coche coche = new Coche();

    public Concesionario() {
        cochesDAO = new CochesDAO();
        pasajeroDAO = new PasajeroDAO();
        sc = new Scanner(System.in);
    }

    //Metodos para manejar coches
    public void addCoche() {
        System.out.println("Ingrese la marca");
        String marca = sc.nextLine();
        System.out.println("Ingrese el modelo");
        String modelo = sc.nextLine();
        System.out.println("Cuantos caballos tiene?");
        int caballos = sc.nextInt();
        sc.nextLine();
        System.out.println("QUe precio tiene?");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.println("Cual es su matricula?");
        String matricula = sc.nextLine();
        Coche coche = new Coche(marca, modelo, caballos, precio, matricula);
        try {
            if (cochesDAO.addCoche(coche)) {
                System.out.println("El coche se ha agregado correctamente");
                System.out.println(coche);
            } else System.out.println("Algo ha fallado al agregar un coche");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarCocheId() {
        System.out.println("Ingrese el id del coche");
        int id = sc.nextInt();
        try {
            if (cochesDAO.buscarCoche(id) != null) {
                System.out.println(cochesDAO.buscarCoche(id));
            } else System.out.println("El id no existe");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarCoche() {
        System.out.println("Ingrese el id del coche a borrar");
        int id = sc.nextInt();
        try {
            System.out.println("Se ha borrado " + cochesDAO.deleteCoche(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCoche() {
        System.out.println("Introduzca la marca");
        String marca = sc.next();
        System.out.println("Introduzca el modelo");
        String modelo = sc.next();
        System.out.println("Cuantos caballos tiene?");
        int caballos = sc.nextInt();
        try {
            int actualizados = cochesDAO.updateCoche(marca, modelo, caballos);
            System.out.println("Se han actualizado " + actualizados);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sacarListado() {
        try {
            ArrayList<Coche> listado = cochesDAO.sacarListado();
            for (Coche coche : listado) {
                System.out.println(coche);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodos para manejar pasajeros
    public void buscarPasajeroId() {
        System.out.println("Ingrese el id del pasajero");
        int id = sc.nextInt();
        try {
            Pasajero pasajero = pasajeroDAO.buscarPorId(id);
            if(pasajero != null) {
                System.out.println(pasajero);
            }else System.out.println("El id no existe");
        } catch (SQLException e) {
            System.out.println("Error al obtener el pasajero");
        }
    }

    public void addPasajero() {
        try {
            System.out.println("Ingrese el nombre del pasajero");
            String nombre = sc.next();
            System.out.println("ingrese la edad del pasajero");
            int edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese el peso del pasajero");
            int peso = sc.nextInt();
            if (pasajeroDAO.addPasajero(new Pasajero(nombre, edad, peso)) > 0) {
                System.out.println("El pasajero se ha agregado correctamente");
            } else System.out.println("No se ha insertado el pasajero");

        } catch (SQLException e) {
            System.out.println("Error al obtener el id del pasajero");
        }

    }
    public void borrarPasajero() {
        System.out.println("Ingrese el id del pasajero");
        int id = sc.nextInt();

        try {
            if(pasajeroDAO.borrarPasajero(id)==1){
                System.out.println("El pasajero se ha eliminado correctamente");
            }else System.out.println("No se ha eliminado el pasajero compruebe el id");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "no se ha eliminado el pasajero, compruebe que el id coincide");
        }
    }
    public void listarPasajeros() {
        ArrayList<Pasajero> listado = new ArrayList<>();
        try {
            listado =pasajeroDAO.obtenerPasajeros();
            for (Pasajero pasajero : listado) {
                System.out.println(pasajero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"no se ha podido obtener el listado de pasajeros");
        }
    }
    public void asignarPasajeroCoche() {
        try {
            System.out.println("Introduzca el id del coche");
            int idCoche = sc.nextInt();
            System.out.println("Introduzca el id del pasajero");
            int idPasajero = sc.nextInt();
            if(pasajeroDAO.asignarPasajeroCoche(idPasajero,idCoche)){
                System.out.println("El pasajero se ha asignado correctamente");
            }else System.out.println("No se ha asignado el pasajero, compruebe que si id concide");
        } catch (SQLException e) {
            System.out.println("Error al sacar listado de coches");
        }
    }
    public void eliminarPasajeroCoche(){
        System.out.println("Introduzca el id del coche");
        int idCoche = sc.nextInt();
        System.out.println("Introduzca el id del pasajero");
        int idPasajero = sc.nextInt();
        try {
            if(pasajeroDAO.eliminarPasajeroCoche(idPasajero,idCoche)){
                System.out.println("El pasajero se ha eliminado correctamente");
            }else System.out.println("No se ha eliminado el pasajero, compruebe que los id conincidan");
        } catch (SQLException e) {
            System.out.println(e.getMessage()+ "error al eliminar pasajero de coche");
        }
    }
    public void listadoPasajerosPorCoche(){
        ArrayList<Pasajero> listado = new ArrayList<>();
        System.out.println("Introduzca el id del coche");
        int idCoche = sc.nextInt();
        sc.nextLine();
        try {
            listado=pasajeroDAO.listarPasajerosCoche(idCoche);
            for (Pasajero pasajero : listado) {
                System.out.println(pasajero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "error al listar pasajeros por coche");
        }
    }

}
