import java.io.Serializable;
import java.util.ArrayList;

public class Curso implements Serializable {
    private int numCreditos;
    private String nome;
    private int codigo;
    private ArrayList<Disciplina> disciplinas;
    static final long serialVersionUID = 3;

    
}
