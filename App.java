import mack.lp2.agendamento.model.Paciente;
import mack.lp2.agendamento.model.Agendamento;
import mack.lp2.agendamento.dao.PacienteDAO;
import mack.lp2.agendamento.dao.AgendamentoDAO;

public class App {
    public static void main(String[] args) throws Exception {
        PacienteDAO pDao = new PacienteDAO();
        AgendamentoDAO aDao = new AgendamentoDAO();

        Paciente p1 = new Paciente(0, "Davi Barros");
        
        pDao.create(p1); 
        System.out.println("Paciente salva com ID: " + p1.getId());

        Agendamento consulta = new Agendamento(0, "15:00", "20/03/2026", p1);
        aDao.create(consulta);
        
        System.out.println("Agendamento realizado!");
    }
}