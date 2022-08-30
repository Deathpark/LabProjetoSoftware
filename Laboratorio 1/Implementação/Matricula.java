import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Matricula implements Serializable {
    private Calendar dataInicio;
    private Calendar dataFim;
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas;
    private double mensalidade;
    static final long serialVersionUID = 5;

    public Matricula() {
        this.dataInicio = Calendar.getInstance();
        this.dataFim = Calendar.getInstance();
        // this.aluno = new Aluno();
        this.disciplinas = new ArrayList<Disciplina>();
        this.mensalidade = 0.0;
    }

    public Matricula(Calendar dataInicio, Calendar dataFim, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.aluno = aluno;
        this.disciplinas = disciplinas;
        this.mensalidade = mensalidade;
    }

    public Matricula(Calendar dataInicio, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
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
        Calendar cal = Calendar.getInstance();            
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);          
        int month = cal.get(Calendar.MONTH);     
             
        if (this.dataInicio.DAY_OF_MONTH <= dayOfMonth && this.dataInicio.MONTH <= month) {           
            return true;            
        } else if (this.dataFim.DAY_OF_MONTH >= dayOfMonth && this.dataFim.MONTH >= month) {            
            return true;          
        } else {           
            return false;           
        }          
    }
    
}
