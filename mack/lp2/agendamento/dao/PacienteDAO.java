package mack.lp2.agendamento.dao;

import mack.lp2.agendamento.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends AbstractDAO{
    public Paciente(){
        this.createTable = "CREATE TABLE IF NOT EXISTS Paciente (id SERIAL PRIMARY KEY, nome VARCHAR(100))";
    }

    @Override
    public Object create (Object obj) throws SQLException{
        Paciente paciente = (Paciente) obj; 
        
        String sql = "INSERT INTO Paciente (nome, id) VALUES (?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.executeUpdate();
            
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                paciente.setId(keys.getInt(1));
            }
        }
        return paciente;
    }
}
