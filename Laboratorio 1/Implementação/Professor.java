import java.util.ArrayList;

public class Professor extends Usuario{
    private ArrayList<Disciplina> disciplinas;

    public Professor() {
        this.nome = "";
        this.senha = "";
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public Professor(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.disciplinas = new ArrayList<Disciplina>();
    }
    
    public Professor(String nome, String senha, ArrayList<Disciplina> disciplinas) {
        this.nome = nome;
        this.senha = senha;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void verificarAlunos(Disciplina disciplina) {
        
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        boolean removeu = this.disciplinas.removeIf(d -> d.equals(disciplina));

        if(removeu) {
            System.out.println("Disciplina removida com sucesso!");
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }
    
    @Override
    public void login() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        
    }


}
