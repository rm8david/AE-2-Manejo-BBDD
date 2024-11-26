package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static Connection connection;
    public Connection getConnection() {
        if(connection == null) {
            newConnection();
        }
        return connection;
    }
    private void newConnection() {
        String url = "jdbc:mysql://localhost/"+DBSchema.DB_NAME;
        try {
            connection = DriverManager.getConnection(url,"root","lolazo11");
        } catch (SQLException e) {
            System.out.println("Error en la conexion a la base de datos"+e.getMessage());
        }
        System.out.println("Conexion creada correctamente");
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion a la base de datos"+e.getMessage());
        }finally {
            connection = null;
        }
    }
}
