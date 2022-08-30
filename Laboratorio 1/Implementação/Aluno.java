import java.util.ArrayList;
import java.util.Scanner;

public class Aluno extends Usuario {
    private ArrayList<Matricula> historico;
    private Matricula matriculaAtual;

    public Aluno(String nome, String senha) {
        super(nome, senha);
    }

    public void Matricular() {

    }

    public void CancelarMatricula() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Aluno))
            return false;
        Aluno a = (Aluno) o;
        return this.nome.equals(a.nome);
    }

    public void menu(Scanner teclado) {

    }
}
