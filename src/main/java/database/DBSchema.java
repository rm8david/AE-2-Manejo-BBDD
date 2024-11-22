package database;

public interface DBSchema {
    String DB_NAME = "concesionario";
    String TAB_CH = "coches";
    String TAB_PA = "pasajeros";
    String COL_ID = "id";
    String COL_CH_MAR = "marca";
    String COL_CH_MO = "modelo";
    String COL_CH_CV = "cv";
    String COL_CH_PRE = "precio";
    String COL_CH_MAT = "matricula";
    String COL_PA_NAME = "nombre";
    String COL_PA_AGE = "edad";
    String COL_PA_WE = "peso";
    String COL_PA_IDCOCHE = "coche_id";
}
