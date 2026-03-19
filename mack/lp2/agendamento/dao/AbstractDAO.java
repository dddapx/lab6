package mack.lp2.agendamento.dao;
import java.sql.*;
import java.util.List;

public abstract class AbstractDAO {
    protected String createTable;

    protected Connection openConnection() throws SQLException {
        String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require&prepareThreshold=0";
        String user = "postgres.rwjcyfaonycwwdhdjlle";
        String password = "isAMAiDCS15tJFer";
        
        return DriverManager.getConnection(url, user, password);
    }

    public abstract Object create(Object obj) throws SQLException;
    public abstract Object read(int idj) throws SQLException;
    public abstract List<Object> readAll() throws SQLException;
    public abstract Object update(Object obj) throws SQLException;
    public abstract Object delete(int id) throws SQLException;
}