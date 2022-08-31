import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    private int numCreditos;
    private String nome;
    private int codigo;
    private ArrayList<Disciplina> disciplinas;
    static final long serialVersionUID = 3;

    public Curso() {
        this.numCreditos = 0;
        this.nome = "";
        this.codigo = 0;
        this.disciplinas = new ArrayList<Disciplina>();
    }    
}
