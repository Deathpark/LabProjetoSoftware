import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina implements Serializable {
    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;
    static final long serialVersionUID = 4;

    private String nome;
    private boolean estaAtiva;
    private boolean obrigatoria;
    private ArrayList<Aluno> alunos;

    public Disciplina() {
        this.nome = "";
        this.estaAtiva = true;
        this.obrigatoria = false;
        this.alunos = new ArrayList<Aluno>();
    }

    public Disciplina(String nome, boolean obrigatoria) {
        this.nome = nome;
        this.estaAtiva = true;
        this.obrigatoria = obrigatoria;
        this.alunos = new ArrayList<Aluno>();
    }

    public Disciplina(String nome, boolean estaAtiva, boolean obrigatoria) {
        this.nome = nome;
        this.estaAtiva = estaAtiva;
        this.obrigatoria = obrigatoria;
        this.alunos = new ArrayList<Aluno>();
    }

    public Disciplina(String nome, boolean estaAtiva, boolean obrigatoria, ArrayList<Aluno> alunos) {
        this.nome = nome;
        this.estaAtiva = estaAtiva;
        this.obrigatoria = obrigatoria;
        this.alunos = alunos;
    }

    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }

    public int getNumMinAlunos() {
        return MIN_ALUNOS;
    }

    public int getNumMaxAlunos() {
        return MAX_ALUNOS;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getObrigatoria() {
        return this.obrigatoria;
    }
    
    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public boolean getEstaAtiva() {
        return this.estaAtiva;
    }

    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    public boolean validarNumALunos() {
        int tamAlunos = this.getQntNumAlunos();
        
        if(tamAlunos == MAX_ALUNOS) {
            return false;
        }
        
        return true;
    }
    
    public int getQntNumAlunos() {
        return this.alunos.size();
    }

    
    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }
    
    public void removerAluno(Aluno aluno) {
        boolean removeu = this.alunos.removeIf(a -> a.equals(aluno));

        if(removeu) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado! Nenhum aluno foi removido");
        }
    }

    public void removerTodosAlunos() {
        this.alunos.removeAll(this.alunos);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Disciplina))
            return false;
            Disciplina d = (Disciplina) o;
        return this.nome.equals(d.nome);
    }

    @Override
    public String toString() {
        return nome ;
    }
}
