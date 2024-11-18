import controller.Concesionario;
import dao.CochesDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args)  {
        Concesionario concesionario = new Concesionario();
        CochesDAO cochesDAO = new CochesDAO();
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1. Añadir un nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consulta coche por ID");
            System.out.println("4. Modificar potencia de un coche");
            System.out.println("5. Listado de coches");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            switch (opcion){
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
            }
        }while(opcion != 6);
    }
}
