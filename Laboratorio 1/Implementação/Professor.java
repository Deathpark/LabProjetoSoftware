import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Professor extends Usuario {
    private ArrayList<Disciplina> disciplinas;
    static final long serialVersionUID = 2;
    static Scanner teclado = new Scanner(System.in);

    public Professor() {
        super("", "");
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public Professor(String nome, String senha) {
        super(nome, senha);
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public Professor(String nome, String senha, ArrayList<Disciplina> disciplinas) {
        super(nome, senha);
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

    public void verificarAlunos() {
        Optional<Disciplina> disciplina;
        Secretaria s = Secretaria.getInstance();
        ArrayList<Disciplina> disciplinas = s.getDisciplinas();

        System.out.println("Digite o nome da disciplina. Deixe em branco para sair");
        String nome = teclado.nextLine();
        disciplina = disciplinas.stream().filter(a -> a.getNome().equals(nome)).findAny();
        if (disciplina.isEmpty() && !nome.equals("")) {
            System.out.println("Essa disciplina não existe!");
        }
        else {
            System.out.println(disciplina.get().getAlunos());
        }

    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        boolean removeu = this.disciplinas.removeIf(d -> d.equals(disciplina));

        if (removeu) {
            System.out.println("Disciplina removida com sucesso!");
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    public void menu(Scanner teclado) {
        int opcao;
        do {
            opcao = opcoes(teclado);

            switch (opcao) {
                case 1:
                    verificarAlunos();
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);

    }

    private static int opcoes(Scanner teclado) {

        limparTela();
        System.out.println("MENU PROFESSOR");
        System.out.println("1 -Verificar alunos em disciplina");

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
