package dao;

import database.DBConection;
import database.DBSchema;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochesDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;




    public boolean addCoche(Coche coche) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
                DBSchema.TAB_CH,DBSchema.COL_CH_MAR,DBSchema.COL_CH_MO,DBSchema.COL_CH_CV,DBSchema.COL_CH_PRE,DBSchema.COL_CH_MAT);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,coche.getMarca());
        preparedStatement.setString(2,coche.getModelo());
        preparedStatement.setInt(3,coche.getCv());
        preparedStatement.setDouble(4,coche.getPrecio());
        preparedStatement.setString(5,coche.getMatricula());
        return preparedStatement.executeUpdate()>0;

    }
    public int deleteCoche(int id) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("DELETE FROM %s WHERE %s = ?", DBSchema.TAB_CH, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
    public Coche buscarCoche(int id) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = ?", DBSchema.TAB_CH, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int idresultado = resultSet.getInt(DBSchema.COL_ID);
            String marca = resultSet.getString(DBSchema.COL_CH_MAR);
            String modelo = resultSet.getString(DBSchema.COL_CH_MO);
            int cv = resultSet.getInt(DBSchema.COL_CH_CV);
            double precio = resultSet.getDouble(DBSchema.COL_CH_PRE);
            String matricula = resultSet.getString(DBSchema.COL_CH_MAT);
            return mapearCoche(idresultado,marca, modelo, cv, precio, matricula);

        }else return null;
    }


    public ArrayList<Coche> sacarListado() throws SQLException {
        connection = new DBConection().getConnection();
        ArrayList<Coche> coches = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", DBSchema.TAB_CH);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt(DBSchema.COL_ID);
            String marca = resultSet.getString(DBSchema.COL_CH_MAR);
            String modelo = resultSet.getString(DBSchema.COL_CH_MO);
            int cv = resultSet.getInt(DBSchema.COL_CH_CV);
            double precio = resultSet.getDouble(DBSchema.COL_CH_PRE);
            String matricula = resultSet.getString(DBSchema.COL_CH_MAT);
            coches.add(mapearCoche(id,marca, modelo, cv, precio, matricula));
        }return coches;
    }
    public int updateCoche(String marca, String modelo, int caballos) throws SQLException {
        connection = new DBConection().getConnection();
        String query = String.format("UPDATE %s SET %s = ? WHERE %s= ? AND %s = ?;",DBSchema.TAB_CH,DBSchema.COL_CH_CV,DBSchema.COL_CH_MAR,DBSchema.COL_CH_MO);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, caballos);
        preparedStatement.setString(2, marca);
        preparedStatement.setString(3, modelo);
        return preparedStatement.executeUpdate();
    }
    private Coche mapearCoche(int id,String marca, String modelo, int cv,double precio, String matricula){
        return new Coche(id,marca,modelo,cv,precio,matricula);
    }
}
