import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        String url = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres?sslmode=require&prepareThreshold=0";
        String user = "postgres.rwjcyfaonycwwdhdjlle"; 
        String password = "isAMAiDCS15tJFer"; 

        System.out.println("Tentando conectar...");
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conectado ao Supabase!");

        String sqlPaciente = "INSERT INTO Paciente (nome, data_nascimento) VALUES ('Ana Silva', '1995-03-15')";
        PreparedStatement stmtP = conn.prepareStatement(sqlPaciente, Statement.RETURN_GENERATED_KEYS);
        stmtP.executeUpdate();
        
        ResultSet keys = stmtP.getGeneratedKeys();
        if (keys.next()) {
            int idGerado = keys.getInt(1);
            String sqlProntuario = "INSERT INTO Prontuario (paciente_id, historico_medico) VALUES (?, ?)";
            PreparedStatement stmtPr = conn.prepareStatement(sqlProntuario);
            stmtPr.setInt(1, idGerado);
            stmtPr.setString(2, "Paciente apresenta quadro leve de rinite alérgica.");
            stmtPr.executeUpdate();
            System.out.println("Paciente Ana Silva e prontuário criados.");
        }

        String sqlUpdate = "UPDATE Paciente SET nome = 'Ana Silva Souza' WHERE nome = 'Ana Silva'";
        conn.createStatement().executeUpdate(sqlUpdate);
        System.out.println("Nome atualizado.");

        String sqlListar = "SELECT p.nome, pr.historico_medico FROM Paciente p " +
                           "JOIN Prontuario pr ON p.id = pr.paciente_id";
        ResultSet rs = conn.createStatement().executeQuery(sqlListar);
        
        while (rs.next()) {
            System.out.println("Nome: " + rs.getString("nome") + " | Histórico: " + rs.getString("historico_medico"));
        }

        String sqlDelete = "DELETE FROM Paciente WHERE nome = 'Ana Silva Souza'";
        int rows = conn.createStatement().executeUpdate(sqlDelete);
        System.out.println("Registros removidos: " + rows);

        conn.close();
    }
}