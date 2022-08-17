import java.util.ArrayList;
import java.util.Date;

public class Matricula {
    private Date dataInicio;
    private Date dataFim;
    private Aluno aluno;
    private ArrayList<Disciplina> disciplinas;
    private double mensalidade;

    public Matricula() {
        this.nome = "";
        this.estaAtiva = true;
        this.obrigatoria = false;
        this.alunos = new ArrayList<Aluno>();
    }

    public Matricula(String nome, boolean estaAtiva, boolean obrigatoria) {
        this.nome = nome;
        this.estaAtiva = estaAtiva;
        this.obrigatoria = obrigatoria;
        this.alunos = new ArrayList<Aluno>();
    }

    public Matricula(String nome, boolean estaAtiva, boolean obrigatoria, ArrayList<Aluno> alunos) {
        this.nome = nome;
        this.estaAtiva = estaAtiva;
        this.obrigatoria = obrigatoria;
        this.alunos = alunos;
    }
}
