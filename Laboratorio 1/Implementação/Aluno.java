import java.util.ArrayList;
import java.util.Scanner;

public class Aluno extends Usuario {
    private ArrayList<Matricula> historico;
    private Matricula matriculaAtual;

    public Aluno(String nome, String senha) {
        super(nome, senha);
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


    public Matricula getMatricula() {
        return this.matriculaAtual;
    }

    public void Matricular() {

    }

    public void cancelarMatricula() {
        if (matriculaAtual != null) {
            matriculaAtual = null;
            System.out.println("Matricula cancelada");
        }
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
