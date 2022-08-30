import java.util.ArrayList;

public class Aluno extends Usuario {
    private ArrayList<Matricula> historico;
    private Matricula matriculaAtual;

    public Aluno() {

    }

    public void Matricular() {

    }

    public void CancelarMatricula() {

    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Aluno))
            return false;
        Aluno a = (Aluno) o;
        return this.nome.equals(a.nome);
    }
}
