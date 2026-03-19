import mack.lp2.agendamento.model.Paciente;
import mack.lp2.agendamento.model.Agendamento;
import mack.lp2.agendamento.dao.PacienteDAO;
import mack.lp2.agendamento.dao.AgendamentoDAO;

public class App {
    public static void main(String[] args) throws Exception {
        PacienteDAO pacDao = new PacienteDAO();
        AgendamentoDAO agDao = new AgendamentoDAO();

        pacDao.initTable();
        agDao.initTable();

        Paciente p1 = new Paciente(0, "Davi Barros", "2000-11-05");
        
        pacDao.create(p1); 
        System.out.println("Paciente salva com ID: " + p1.getId());

        Agendamento consulta = new Agendamento(0, "15:00", "20/03/2026", p1);
        agDao.create(consulta);
        
        System.out.println("Agendamento realizado!");

        System.out.println("\nLista de Agendamentos no Banco:");
        for (Object obj : agDao.readAll()) {
            Agendamento ag = (Agendamento) obj;
            System.out.println("Dia: " + ag.getDia() + " | Hora: " + ag.getHorario() + " | Paciente: " + ag.getPaciente().getNome());
        }
    }
}