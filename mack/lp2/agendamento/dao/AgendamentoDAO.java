package mack.lp2.agendamento.dao;

import mack.lp2.agendamento.model.Agendamento;
import mack.lp2.agendamento.model.Paciente;

import java.sql.*;

public class AgendamentoDAO extends AbstractDAO{
    public AgendamentoDAO() {
        this.createTable = "CREATE TABLE IF NOT EXISTS Agendamento (" +
                           "id SERIAL PRIMARY KEY, " +
                           "horario VARCHAR(100), " +
                           "dia VARCHAR(100), " +
                           "paciente_id INT, " +
                           "FOREIGN KEY (paciente_id) REFERENCES Paciente(id))";
    }

    @Override
    public Object create(Object obj) throws SQLException {
        Agendamento a = (Agendamento) obj;
        String sql = "INSERT INTO Agendamento (horario, dia, paciente_id) VALUES (?, ?, ?)";
        
        try (Connection conn = openConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, a.getHorario());
            stmt.setString(2, a.getDia());
            stmt.setInt(3, a.getPaciente().getId()); 
            
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) a.setId(rs.getInt(1));
        }
        return a;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        Agendamento a = (Agendamento) obj;
        String sql = "UPDATE Agendamento SET horario = ?, dia = ?, paciente_id = ? WHERE id = ?";
        
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, a.getHorario());
            stmt.setString(2, a.getDia());
            stmt.setInt(3, a.getPaciente().getId());
            stmt.setInt(4, a.getId());
            stmt.executeUpdate();
        }
        return a;
    }

    @Override
    public Object delete(int id) throws SQLException {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
        
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; 
        }
    }

    @Override
    public Object read(int id) throws SQLException {
        String sql = "SELECT a.id, a.horario, a.dia, p.id AS id_paciente, p.nome, p.data_nascimento " +
                     "FROM Agendamento a INNER JOIN Paciente p ON a.paciente_id = p.id";
                     
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Paciente p = new Paciente(rs.getInt("id_paciente"), rs.getString("nome"), rs.getString("data_nascimento"));
                return new Agendamento(rs.getInt("id"), rs.getString("horario"), rs.getString("dia"), p);
            }
        }
        return null;
    }

    @Override
    public java.util.List<Object> readAll() throws SQLException {
        java.util.List<Object> lista = new java.util.ArrayList<>();
        String sql = "SELECT a.id, a.horario, a.dia, p.id AS id_paciente, p.nome, p.data_nascimento " +
                     "FROM Agendamento a INNER JOIN Paciente p ON a.paciente_id = p.id";
                     
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Paciente p = new Paciente(rs.getInt("id_paciente"), rs.getString("nome"), rs.getString("data_nascimento"));
                lista.add(new Agendamento(rs.getInt("id"), rs.getString("horario"), rs.getString("dia"), p));
            }
        }
        return lista;
    }
}