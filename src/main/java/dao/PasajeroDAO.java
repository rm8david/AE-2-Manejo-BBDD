package dao;

import database.DBConection;
import database.DBSchema;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public Pasajero buscarPorId(int id) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",DBSchema.TAB_PA,DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int idResultado = resultSet.getInt(DBSchema.COL_ID);
            String nombre = resultSet.getString(DBSchema.COL_PA_NAME);
            int edad = resultSet.getInt(DBSchema.COL_PA_AGE);
            int peso = resultSet.getInt(DBSchema.COL_PA_WE);
            int cocheId = resultSet.getInt(DBSchema.COL_PA_IDCOCHE);
            return mapearPasajero(idResultado, nombre, edad, peso, cocheId);
        }
        return null;
    }

    private Pasajero mapearPasajero(int idResultado, String nombre, int edad, int peso, int cocheId) {
        return new Pasajero(idResultado, nombre, edad, peso, cocheId);
    }
}
