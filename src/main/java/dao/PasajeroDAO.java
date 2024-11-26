package dao;

import database.DBConection;
import database.DBSchema;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public int addPasajero(Pasajero pasajero) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES(?,?,?)",DBSchema.TAB_PA,DBSchema.COL_PA_NAME,DBSchema.COL_PA_AGE,DBSchema.COL_PA_WE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setInt(3, pasajero.getPeso());
        return preparedStatement.executeUpdate();
    }
    public int borrarPasajero(int id) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("DELETE FROM %s WHERE %s = ?",DBSchema.TAB_PA,DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
    public ArrayList<Pasajero> obtenerPasajeros() throws SQLException{
        connection = new DBConection().getConnection();
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        String query = String.format("SELECT * FROM %s",DBSchema.TAB_PA);
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(DBSchema.COL_ID);
            String nombre = resultSet.getString(DBSchema.COL_PA_NAME);
            int edad = resultSet.getInt(DBSchema.COL_PA_AGE);
            int peso = resultSet.getInt(DBSchema.COL_PA_WE);
            int cocheId = resultSet.getInt(DBSchema.COL_PA_IDCOCHE);
            pasajeros.add(mapearPasajero(id, nombre, edad, peso, cocheId));
        }return pasajeros;
    }
    public boolean asignarPasajeroCoche(int idPasajero, int idCoche) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",DBSchema.TAB_PA,DBSchema.COL_PA_IDCOCHE,DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        preparedStatement.setInt(2, idPasajero);
        return preparedStatement.executeUpdate() > 0;
    }
    public boolean eliminarPasajeroCoche(int idPasajero, int idCoche) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ? AND %s=?",DBSchema.TAB_PA,DBSchema.COL_PA_IDCOCHE,DBSchema.COL_ID,DBSchema.COL_PA_IDCOCHE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setNull(1,java.sql.Types.INTEGER);
        preparedStatement.setInt(2, idPasajero);
        preparedStatement.setInt(3, idCoche);
        return preparedStatement.executeUpdate() > 0;
    }
    public ArrayList<Pasajero> listarPasajerosCoche(int idCoche) throws SQLException {
        connection = new DBConection().getConnection();
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s =?",DBSchema.TAB_PA,DBSchema.COL_PA_IDCOCHE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(DBSchema.COL_ID);
            String nombre = resultSet.getString(DBSchema.COL_PA_NAME);
            int edad = resultSet.getInt(DBSchema.COL_PA_AGE);
            int peso = resultSet.getInt(DBSchema.COL_PA_WE);
            int cocheId = resultSet.getInt(DBSchema.COL_PA_IDCOCHE);
            pasajeros.add(new Pasajero(id, nombre, edad, peso, cocheId));
        }return pasajeros;
    }

    private Pasajero mapearPasajero(int idResultado, String nombre, int edad, int peso, int cocheId) {
        return new Pasajero(idResultado, nombre, edad, peso, cocheId);
    }
}
