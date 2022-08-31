import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Optional;
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

    public void matricular(Scanner teclado) {
        if (matriculaAtual == null) {
            System.out.println("Você já possui uma matricula ativa!");
            return;
        }
        Secretaria s = Secretaria.getInstance();
        ArrayList<Disciplina> disciplinas = s.getDisciplinas();
        ArrayList<Disciplina> disciplinasMatricula = new ArrayList<>();
        System.out.println("Adicionar disciplinas");
        Optional<Disciplina> disciplina;
        boolean erro;
        int opcao = 1;

        while (opcao == 1) {
            do {
                System.out.println("Digite o nome da disciplina. Deixe em branco para sair");
                String nome = teclado.nextLine();
                disciplina = disciplinas.stream().filter(a -> a.getNome().equals(nome)).findAny();
                if (disciplina.isEmpty() && !nome.equals("")) {
                    erro = true;
                    System.out.println("Essa disciplina não existe! Tente novamente.");
                } else
                    erro = false;
            } while (erro);

            if (disciplina.isPresent()) {
                disciplinasMatricula.add(disciplina.get());
            }
            System.out.println("Deseja adicionar mais disciplinas? (1 - Sim | 2 - Não)");
            opcao = teclado.nextInt();
        }

        if (disciplinasMatricula.size() > 0) {
            matriculaAtual = new Matricula(LocalDate.now(), this, disciplinasMatricula, 1000);
            historico.add(matriculaAtual);
            System.out.println("Matricula realizada!");
        }

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
        int opcao;
        do {
            opcao = opcoes(teclado);

            switch (opcao) {
                case 1:
                    matricular(teclado);
                    break;
                case 2:
                    cancelarMatricula();
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);

    }

    private static int opcoes(Scanner teclado) {

        limparTela();
        System.out.println("MENU ALUNO");
        System.out.println("1 - Matricular.");
        System.out.println("2 - Cancelar matricula.");

        int opcao = 0;
        try {
            opcao = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("Somente opções numéricas.");
            opcao = -1;
        }
        return opcao;
    }
}
