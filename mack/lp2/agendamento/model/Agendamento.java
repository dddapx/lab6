package mack.lp2.agendamento.model;

public class Agendamento {
    private int id;
    private String horario;
    private String dia;
    private Paciente paciente;

    public Agendamento(int id, String horario, String dia, Paciente paciente) {
        this.id = id;
        this.horario = horario;
        this.dia = dia;
        this.paciente = paciente;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
