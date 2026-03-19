package mack.lp2.agendamento.dao;

import mack.lp2.agendamento.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends AbstractDAO{
    public PacienteDAO(){
        this.createTable = "CREATE TABLE IF NOT EXISTS Paciente (id SERIAL PRIMARY KEY, nome VARCHAR(100))";
    }

    @Override
    public Object create(Object obj) throws SQLException {
        Paciente p = (Paciente) obj;
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Paciente (nome, data_nascimento) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(p.getDataNascimento()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) p.setId(rs.getInt(1));
        }
        return p;
    }

    @Override
    public List<Object> readAll() throws SQLException {
        List<Object> lista = new ArrayList<>();
        try (Connection conn = openConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Paciente")) {
            while (rs.next()) {
                lista.add(new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("data_nascimento").toString()));
            }
        }
        return lista;
    }

    @Override
    public Object update(Object obj) throws SQLException {
        Paciente p = (Paciente) obj;
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Paciente SET nome = ? WHERE id = ?")) {
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getId());
            stmt.executeUpdate();
        }
        return p;
    }

    @Override
    public Object delete(int id) throws SQLException {
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Paciente WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Object read(int id) throws SQLException {
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Paciente WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("data_nascimento").toString());
        }
        return null;
    }
}