package controller;

import dao.CochesDAO;
import model.Coche;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {
    private CochesDAO cochesDAO;
    Scanner sc = new Scanner(System.in);
    Coche coche = new Coche();
    public Concesionario() {
        cochesDAO = new CochesDAO();
        sc = new Scanner(System.in);
    }

    public void addCoche(){
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
        Coche coche = new Coche(marca,modelo,caballos,precio,matricula);
        try {
            if(cochesDAO.addCoche(coche)){
                System.out.println("El coche se ha agregado correctamente");
                System.out.println(coche);
            }else System.out.println("Algo ha fallado al agregar un coche");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void buscarCocheId(){
        System.out.println("Ingrese el id del coche");
        int id = sc.nextInt();
        try {
            if(cochesDAO.buscarCoche(id)!=null){
                System.out.println(cochesDAO.buscarCoche(id));
            }else System.out.println("El id no existe");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void borrarCoche(){
        System.out.println("Ingrese el id del coche a borrar");
        int id = sc.nextInt();
        try {
            System.out.println("Se ha borrado "+cochesDAO.deleteCoche(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarCoche(){
        System.out.println("Introduzca la marca");
        String marca = sc.nextLine();
        System.out.println("Introduzca el modelo");
        String modelo = sc.nextLine();
        System.out.println("Cuantos caballos tiene?");
        int caballos = sc.nextInt();
        try {
            int actualizados = cochesDAO.updateCoche(marca,modelo,caballos);
            System.out.println("Se han actualizado "+actualizados);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sacarListado(){
        try {
            ArrayList<Coche> listado = cochesDAO.sacarListado();
            for(Coche coche : listado){
                System.out.println(coche);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
