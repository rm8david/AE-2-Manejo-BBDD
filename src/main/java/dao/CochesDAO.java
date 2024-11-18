package dao;

import database.DBConection;
import database.DBSchema;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CochesDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO(){
        connection = new DBConection().getConnection();
    }
    public boolean addCoche(Coche coche) throws SQLException {
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
        String query = String.format("DELETE FROM %s WHERE %s = ?", DBSchema.TAB_CH, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }
    public Coche buscarCoche(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = ?", DBSchema.TAB_CH, DBSchema.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String marca = resultSet.getString(DBSchema.COL_CH_MAR);
            String modelo = resultSet.getString(DBSchema.COL_CH_MO);
            int cv = resultSet.getInt(DBSchema.COL_CH_CV);
            double precio = resultSet.getDouble(DBSchema.COL_CH_PRE);
            String matricula = resultSet.getString(DBSchema.COL_CH_MAT);
            return mapearCoche(marca, modelo, cv, precio, matricula);
            //System.out.println(coche);
        }else return null;
    }
    private Coche mapearCoche(String marca, String modelo, int cv,double precio, String matricula){
        return new Coche(marca,modelo,cv,precio,matricula);
    }
}
