import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Matricula implements Serializable {
    private Date dataInicio;
    private Date dataFim;
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas;
    private double mensalidade;
    static final long serialVersionUID = 5;

    public Matricula() {
        this.dataInicio = new Date();
        this.dataFim = new Date();
        // this.aluno = new Aluno();
        this.disciplinas = new ArrayList<Disciplina>();
        this.mensalidade = 0.0;
    }

    public Matricula(Date dataInicio, Date dataFim, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.aluno = aluno;
        this.disciplinas = disciplinas;
        this.mensalidade = mensalidade;
    }

    public Matricula(Date dataInicio, Aluno aluno, ArrayList<Disciplina> disciplinas, double mensalidade) {
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
        //verificar periodo aberto
        this.disciplinas.remove(disciplina);
    }

    public boolean estaEmAberto() {
        //logica para aberto/fechado
        return true;
    }
}
