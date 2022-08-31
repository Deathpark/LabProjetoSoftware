import java.io.Serializable;
import java.util.ArrayList;

public class Semestre implements Serializable {
    private ArrayList<Disciplina> disciplinas;
    private int codigo;
    static final long serialVersionUID = 6;

    public Semestre (){
        this.disciplinas = new ArrayList<Disciplina>();
        this.codigo = 0;
    }

    public Semestre(int codigo, ArrayList<Disciplina> disciplinas) {
        this.codigo = codigo;
        this.disciplinas = disciplinas;
    }

    public void ValidaCodigo(){
        
    }
    
}
