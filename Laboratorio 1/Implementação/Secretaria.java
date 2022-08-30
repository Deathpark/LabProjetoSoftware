import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Secretaria extends Usuario {
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Disciplina> disciplinas;
    private String ARQ_ALUNOS = "./arquivos/alunos.bin";
    private String ARQ_PROFESSORES = "./arquivos/professores.bin";
    private String ARQ_DISCIPLINAS = "./arquivos/disciplinas.bin";
    private static final String OPCAO_MENU_PROFESSOR = "professor(a)";
    private static final String OPCAO_MENU_ALUNO = "aluno(a)";
    private static final String OPCAO_MENU_DISCIPLINA = "disciplina";
    private static Secretaria secretaria = new Secretaria();

    public static Secretaria getInstance() {
        return secretaria;
    }

    private Secretaria() {
        super("secretaria", "senha");
        this.alunos = lerDados(ARQ_ALUNOS);
        this.disciplinas = lerDados(ARQ_DISCIPLINAS);
        this.professores = lerDados(ARQ_PROFESSORES);
    }

    public void salvar() {
        escreverDados(alunos, ARQ_ALUNOS);
        escreverDados(professores, ARQ_PROFESSORES);
        escreverDados(disciplinas, ARQ_DISCIPLINAS);
    }

    public void visualizarTodosOsAlunos() {
        for (Aluno d : alunos) {
            System.out.println(d);
        }
    }

    public void gerarCurriculoSemestre() {

    }

    public Disciplina criarDisciplina(String nome, boolean estaAtiva, boolean obrigatoria) {
        Disciplina d = new Disciplina(nome, estaAtiva, obrigatoria);
        disciplinas.add(d);
        return d;
    }

    public void atualizarDisciplina(Disciplina disciplina) {

    }

    public void visualizarDisciplina(Disciplina disciplina) {
        System.out.println(disciplina.toString());
    }

    public void excluirDisciplina(Disciplina disciplina) {
        boolean removeu = this.disciplinas.removeIf(d -> d.equals(disciplina));

        if (removeu) {
            System.out.println("Disciplina removida com sucesso!");
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    public void atualizarAluno(Aluno aluno) {
        int resposta = 1;
        while (resposta > 0) {
        }
        Aluno alunoAux = this.alunos.stream().filter(a -> a.equals(aluno)).findFirst().get();
    }

    public Aluno criarAluno(String nome, String senha) {
        if (nome.equals("")) {
            return null;
        }
        Aluno a = new Aluno(nome, senha);
        alunos.add(a);
        return a;

    }

    public void excluirAluno(Aluno aluno) {
        boolean removeu = this.alunos.removeIf(a -> a.equals(aluno));

        if (removeu) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public void visualizarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
    }

    public void atualizarProfessor(Professor professor) {
        Scanner scanner = new Scanner(System.in);

        this.menuAtualizacaoCadastros(OPCAO_MENU_PROFESSOR);
        int opcao = scanner.nextInt();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    System.out.println("Digite o novo nome do Professor:");
                    professor.setNome(scanner.nextLine());
                    System.out.println("Nome modificado com sucesso!\n\n");
                    break;
                case 2:
                    System.out.println("Digite a nova senha do Professor:");
                    professor.setSenha(scanner.nextLine());
                    break;
                case 3:
                    int opcao2 = 1;
                    while (opcao2 == 1 || opcao2 == 2) {
                        System.out.println("1 - Adicionar disciplinas | 2 - Remover disciplinas");
                        System.out.println("Digite qualquer outro número para finalizar.");
                        opcao2 = scanner.nextInt();

                        if (opcao2 == 1) {
                            Disciplina disciplina = this.buscaDisciplina(scanner);

                            if (disciplina != null) {
                                professor.adicionarDisciplina(disciplina);
                                System.out.println("Disciplina adicionada com sucesso!");
                            } else {
                                System.out.println("Disciplina não encontrada!");
                            }
                        } else if (opcao2 == 2) {
                            professor.removerDisciplina(this.buscaDisciplina(scanner));
                        }
                    }
                    break;
                default:
                    System.out.println("Opção desconhecida! Assumindo sair.");
                    opcao = 0;
                    break;
            }

            this.menuAtualizacaoCadastros(OPCAO_MENU_PROFESSOR);
        }

        System.out.println("Atualização do professor feita com sucesso!");
    }

    public void criarProfessor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Professor:");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha do Professor:");
        String senha = scanner.nextLine();
        System.out.println("Deseja adicionar disciplinas para este novo professor?");
        System.out.println("1 - Sim | 2 - Não");
        int opcao = scanner.nextInt();

        ArrayList<Disciplina> discAdd = new ArrayList<Disciplina>();
        if (opcao == 1) {
            int opcao2 = 1;
            while (opcao2 == 1) {
                Disciplina disciplina = this.buscaDisciplina(scanner);

                if (disciplina != null) {
                    discAdd.add(disciplina);
                    System.out.println("Disciplina adicionada!");
                } else {
                    System.out.println("Disicplina não encontrada!");
                }

                System.out.println("\nDeseja adicionar mais disciplinas?");
                opcao2 = scanner.nextInt();
            }
        } else if (opcao != 2) {
            System.out.println("Opção desconhecida! Assumindo não.");
        }

        this.professores.add(new Professor(nome, senha, disciplinas));
        System.out.println("\n\nProfessor adicionado!");

        scanner.close();
    }

    public void excluirProfessor(Professor professor) {
        boolean removeu = this.professores.removeIf(p -> p.equals(professor));

        if (removeu) {
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public void visualizarProfessor(Professor professor) {
        System.out.println(professor.toString());
    }

    public void menuAtualizacaoCadastros(String opcao) {
        // Bloco comum
        System.out.println("Qual característica do(a) " + opcao + " será modificada?");
        System.out.println("1 - Nome do(a) " + opcao + ".");
        System.out.println("2 - Senha do(a) " + opcao + ".");

        // Bloco específico
        if (opcao.equals("aluno(a)")) {
            System.out.println("3 - Matricula atual do(a) " + opcao + ".");
        } else if (opcao.equals("professor(a)")) {
            System.out.println("3 - Disciplinas que o(a) " + opcao + " leciona.");
        } else if (opcao.equals("disciplina")) {
            System.out.println("3 - Nome da " + opcao);
        }

        // Bloco comum final
        System.out.println("0 - Sair");
    }

    public Disciplina buscaDisciplina(Scanner scanner) {
        System.out.println("Digite o nome da disciplina:");
        String nomeDisciplina = scanner.nextLine();

        return this.disciplinas.stream().filter(d -> d.getNome().equals(nomeDisciplina)).findFirst().get();

    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> arr = new ArrayList<>();
        arr.addAll(alunos);
        arr.addAll(professores);
        arr.add(this);
        return arr;
    }

    // #region menu

    public void menu(Scanner teclado) {
        int opcao;
        do {
            opcao = opcoes(teclado);

            switch (opcao) {
                case 1:
                    // menu CRUD discipina
                    break;
                case 2:
                    // menu CRUD prof
                    break;
                case 3:
                    menuAluno(teclado);
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);
    }

    private static int opcoes(Scanner teclado) {
        limparTela();
        System.out.println("MENU SECRETARIA");
        System.out.println("Selecione o que gerenciar:");
        System.out.println("1 - Disciplina");
        System.out.println("2 - Professor");
        System.out.println("3 - Aluno");
        System.out.println("0 - Sair");

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

    public void menuAluno(Scanner teclado) {
        int opcao;
        do {
            opcao = opcoesCRUD(teclado, OpcoesSecretaria.OPCAO_MENU_ALUNO);

            switch (opcao) {
                case 1:
                    Aluno a = getAlunoByNome(teclado);
                    if (a != null)
                        visualizarAluno(a);
                    break;
                case 2:
                    // criar
                    break;

                case 3:
                    // editar
                    break;
                case 4:
                    Aluno excluir = getAlunoByNome(teclado);
                    excluirAluno(excluir);
                    break;
                case 5:
                    visualizarTodosOsAlunos();
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);
    }

    private static int opcoesCRUD(Scanner teclado, OpcoesSecretaria opcaoSecretaria) {
        limparTela();
        System.out.println("MENU " + opcaoSecretaria.toString().toUpperCase());
        System.out.println("Selecione a ação:");
        System.out.println("1 - Visualizar " + opcaoSecretaria);
        System.out.println("2 - Criar novo(a) " + opcaoSecretaria);
        System.out.println("3 - Editar " + opcaoSecretaria);
        System.out.println("4 - Excluir " + opcaoSecretaria);
        System.out.println("5 - Visualizar todos(as) " + opcaoSecretaria + "s");
        System.out.println("0 - Sair");

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

    private Aluno getAlunoByNome(Scanner teclado) {
        Optional<Aluno> aluno;
        boolean erro = false;

        do {
            System.out.println("Digite o nome. Deixe em branco para sair");
            String nome = teclado.nextLine();
            aluno = alunos.stream().filter(a -> a.nome.equals(nome)).findAny();
            if (aluno.isEmpty() && !nome.equals("")) {
                erro = true;
                System.out.println("Esse aluno não existe! Tente novamente.");
            } else
                erro = false;
        } while (erro);

        if (aluno.isPresent()) {
            return aluno.get();
        }
        return null;
    }

    // #endregion

    // #region Leitura e Escrita
    public static <T> void escreverDados(ArrayList<T> dados, String nomeArq) {

        try {
            FileOutputStream fout = new FileOutputStream(nomeArq);
            ObjectOutputStream arqSaida = new ObjectOutputStream(fout);
            arqSaida.writeObject(dados);
            arqSaida.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro na escrita de dados: arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro na escrita de dados: " + e);
        }
    }

    public static <T> ArrayList<T> lerDados(String nomeArq) {

        ArrayList<T> dados = new ArrayList<>();
        FileInputStream arq;
        try {
            arq = new FileInputStream(nomeArq);

            ObjectInputStream arqLeitura;
            arqLeitura = new ObjectInputStream(arq);

            dados = (ArrayList<T>) arqLeitura.readObject();

            arqLeitura.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura de dados: arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro na leitura de dados: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro na leitura de dados: Os dados não estão no formato correto");
        }

        return dados;
    }
    // #endregion
}
