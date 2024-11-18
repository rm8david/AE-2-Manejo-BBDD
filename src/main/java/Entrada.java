import controller.Concesionario;
import dao.CochesDAO;

import java.sql.SQLException;

public class Entrada {
    public static void main(String[] args)  {
        Concesionario concesionario = new Concesionario();
        CochesDAO cochesDAO = new CochesDAO();
        //concesionario.buscarCocheId();
        //concesionario.borrarCoche();
        concesionario.addCoche();
    }
}
