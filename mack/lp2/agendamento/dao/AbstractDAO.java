package mack.lp2.agendamento.dao;
import java.sql.*;
import java.util.List;

public abstract class AbstractDAO {
    String databaseURL = "";

    private Connection openConnection(){
        Connection conn = DriverManager.getConnection(
         "jdbc:postgre" + databaseURL + "INIT-" + createTable, "admin", "admin"
        );
        return conn;
    }

    public abstract Object create(Object obj) throws SQLException;
    public abstract Object read(int idj) throws SQLException;
    public abstract List<Object> readAll() throws SQLException;
    public abstract Object update(Object obj) throws SQLException;
    public abstract Object delete(int id) throws SQLException;
}
