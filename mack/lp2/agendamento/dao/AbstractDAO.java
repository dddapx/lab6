package mack.lp2.agendamento.dao;
import java.sql.*;
import java.util.List;

public abstract class AbstractDAO {
    protected String createTable;

    public AbstractDAO (){
        this.createTable = "CREATE TABLE IF NOT EXISTS Agendamento (" +
                           "id SERIAL PRIMARY KEY, " +
                           "horario VARCHAR(255), " +
                           "dia VARCHAR(255), " +
                           "paciente_id INT, " +
                           "FOREIGN KEY (paciente_id) REFERENCES Paciente(id))";
    }

    private Connection openConnection(){
        String databaseURL = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require&prepareThreshold=0";
        String user = "postgres.rwjcyfaonycwwdhdjlle";
        String password = "isAMAiDCS15tJFer";
        Connection conn = DriverManager.getConnection(
         "jdbc:postgresql://localhost:5432/nome_do_banco" + databaseURL + "INIT-" + createTable, user, password
        );
        return conn;
    }

    public abstract Object create(Object obj) throws SQLException;
    public abstract Object read(int idj) throws SQLException;
    public abstract List<Object> readAll() throws SQLException;
    public abstract Object update(Object obj) throws SQLException;
    public abstract Object delete(int id) throws SQLException;
}
