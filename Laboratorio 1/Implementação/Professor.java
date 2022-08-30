import java.util.ArrayList;

public class Professor extends Usuario {
    private int turmas;
    private ArrayList<Disciplina> disciplinas;
    static final long serialVersionUID = 2;

    public Professor(String nome, String senha) {
        super(nome, senha);
    }

    public void verificarAlunos(Disciplina disciplina) {
        
    }
}
