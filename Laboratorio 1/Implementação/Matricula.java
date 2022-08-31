import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Matricula implements Serializable {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas;
    private double mensalidade;
    static final long serialVersionUID = 5;

    public Matricula() {
        this.dataInicio = LocalDate.now();
        this.dataFim = LocalDate.now();
        // this.aluno = new Aluno();
        this.disciplinas = new ArrayList<Disciplina>();
        this.mensalidade = 0.0;
    }

    public Matricula(LocalDate dataInicio, LocalDate dataFim, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.aluno = aluno;
        this.disciplinas = disciplinas;
        this.mensalidade = mensalidade;
    }

    public Matricula(LocalDate dataInicio, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.aluno = aluno;
        this.disciplinas = disciplinas;
        this.mensalidade = mensalidade;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        if(this.estaEmAberto()) {
            this.disciplinas.remove(disciplina);
        }
        System.out.println("Período indisponível para remover matrícula");
    }


    public boolean estaEmAberto() {
        LocalDate date = LocalDate.now();         
        int dayOfMonth = date.getDayOfMonth();     
        int month = date.getMonthValue();    
             
        if (this.dataInicio.getDayOfMonth() < dayOfMonth && this.dataInicio.getMonthValue() <= month) {           
            return true;            
        } else {           
            return false;           
        }          
    }
    
}
