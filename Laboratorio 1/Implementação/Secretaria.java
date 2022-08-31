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

    public void gerarCurriculoSemestre(Scanner scanner) {
        System.out.println("Digite o código do semestre:");
        int codigo = scanner.nextInt();
        
        System.out.println("Deseja adicionar matérias para esse semestre? (1 - Sim | 2 - Não)");
        int opcao = scanner.nextInt();
        
        ArrayList<Disciplina> disciplinasAdicionar = new ArrayList<Disciplina>();
        while(opcao == 1) {
            System.out.println("Digite o nome da disciplina: ");
            String nome = scanner.nextLine();

            Disciplina disc = this.disciplinas.stream().filter(d -> d.getNome().equals(nome)).findFirst().get();
            if(disc != null) {
                disciplinasAdicionar.add(disc);
            } else {
                System.out.println("Disciplina não encontrada!");
            }

            System.out.println("Deseja adicionar mais matérias? (1 - Sim | 2 - Não)");
            opcao = scanner.nextInt();
        }

        Semestre semestre = new Semestre(codigo, disciplinasAdicionar);
    }

    // #region disciplina
    public void criarDisciplina(Scanner scanner) {
        System.out.println("Digite o nome da disciplina:");
        String nome = scanner.nextLine();
        System.out.println("Digite se a disciplina é obrigatória: (S/N)");
        String obrigatoria = scanner.nextLine().toUpperCase();

        Optional<Disciplina> disciplinaExiste = this.disciplinas.stream().filter(d -> d.getNome().equals(nome)).findFirst();

        boolean obr;
        switch (obrigatoria) {
            case "S":
                obr = true;
                break;
            case "N":
                obr = false;
                break;
            default:
                System.out.println("Opção desconhecida. Assumindo Não.");
                obr = false;
                break;
        }

        if(disciplinaExiste.isPresent()) {
            System.out.println("Já existe uma disciplina com este nome!");
        } else {
            this.disciplinas.add(new Disciplina(nome, obr));
            System.out.println("\n\nDisciplina adicionada!");
        }
    }

    public void atualizarDisciplina(Disciplina disciplina, Scanner scanner) {
        this.menuAtualizacaoDisciplina();
        int opcao = scanner.nextInt();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    System.out.println("Digite o novo nome da disciplina:");
                    disciplina.setNome(scanner.nextLine());
                    System.out.println("Nome modificado com sucesso!\n\n");
                    break;
                case 2:
                    System.out.println("Digite a nova obrigatoriedade da disciplina: (S/N)");
                    String obr = scanner.nextLine().toUpperCase();

                    if (obr.equals("S")) {
                        disciplina.setObrigatoria(true);
                    } else {
                        if (!obr.equals("N")) {
                            System.out.println("Opção desconhecida, assumindo Não.");
                        }
                        disciplina.setObrigatoria(false);
                    }
                    break;
                case 3:
                    int qntAlunos = disciplina.getQntNumAlunos();
                    if (qntAlunos < disciplina.getNumMinAlunos()) {
                        System.out.println("Disciplina com número de alunos inferior ao mínimo.");
                        System.out.println("Deseja inativar a disciplina? (S/N)");
                        String opcao2 = scanner.nextLine().toUpperCase();

                        if (opcao2.equals("S")) {
                            disciplina.setObrigatoria(true);
                        } else {
                            if (!opcao2.equals("N")) {
                                System.out.println("Opção desconheciaa, assumindo Não.");
                            }
                            disciplina.setObrigatoria(false);
                        }
                    } else if (qntAlunos == disciplina.getNumMaxAlunos()) {
                        System.out.println("Disciplina com a capacidade máxima de alunos!");
                        System.out.println("A quantidade de alunos é: " + qntAlunos);
                    } else {
                        System.out.println("A disciplina está com: " + qntAlunos);
                    }
                    break;
                case 4:
                    int opcao2 = 1;
                    while (opcao2 == 1 || opcao2 == 2 || opcao2 == 3) {
                        System.out.println("Qual operação deseja realizar?");
                        System.out.println("1 - Adicionar aluno");
                        System.out.println("2 - Remover aluno");
                        System.out.println("2 - Remover todos os alunos da disciplina");

                        switch (opcao2) {
                            case 1:
                                if (disciplina.validarNumALunos()) {
                                    Aluno aluno = this.getAlunoByNome(scanner);

                                    if (aluno != null) {
                                        disciplina.adicionarAluno(aluno);
                                    }
                                } else {
                                    System.out.println("A disciplina não pode receber novos alunos.");
                                }
                                break;
                            case 2:
                                Aluno aluno = this.getAlunoByNome(scanner);

                                if (aluno != null) {
                                    disciplina.removerAluno(aluno);
                                }
                                break;
                            case 3:
                                System.out.println("Deseja realmente remover todos os alunos da disciplina? (S/N)");
                                String opcao3 = scanner.nextLine().toUpperCase();

                                if (opcao3.equals("S")) {
                                    disciplina.removerTodosAlunos();
                                } else {
                                    if (!opcao3.equals("N")) {
                                        System.out.println("Opção desconhecida, assumindo Não.");
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Opção desconhecida! Assumindo sair.");
                    opcao = 0;
                    break;
            }

            this.menuAtualizacaoDisciplina();
        }
    }

    public void excluirDisciplina(Disciplina disciplina) {
        boolean removeu = this.disciplinas.removeIf(d -> d.equals(disciplina));

        if (removeu) {
            System.out.println("Disciplina removida com sucesso!");
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    public void visualizarDisciplina(Disciplina disciplina) {
        System.out.println(disciplina.toString());
    }

    public void visualizarTodasAsDisciplinas() {
        this.disciplinas.stream().forEach((disciplina) -> {
            System.out.println(disciplina);
        });
    }

    // #endregion

    // #region aluno
    public void atualizarAluno(Aluno aluno, Scanner scanner) {
        this.menuAtualizacaoCadastros(OPCAO_MENU_ALUNO);
        int opcao = scanner.nextInt();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    System.out.println("Digite o novo nome do(a) Aluno(a):");
                    aluno.setNome(scanner.nextLine());
                    System.out.println("Nome modificado com sucesso!\n\n");
                    break;
                case 2:
                    System.out.println("Digite a nova senha do(a) Aluno(a):");
                    aluno.setSenha(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Matrícula atual do aluno, você deseja:");
                    int opcao2 = 1;
                    while (opcao2 == 1 || opcao2 == 2) {
                        System.out.println("1 - Adicionar disciplina | 2 - Remover disciplina");
                        System.out.println("Digite qualquer outro número para finalizar.");
                        opcao2 = scanner.nextInt();

                        if (opcao2 == 1) {
                            Optional<Disciplina> disciplina = this.buscaDisciplina(scanner);

                            if (disciplina.isPresent()) {
                                aluno.getMatricula().adicionarDisciplina(disciplina.get());
                                System.out.println("Disciplina adicionada com sucesso!");
                            } else {
                                System.out.println("Disciplina não encontrada!");
                            }
                        } else if (opcao2 == 2) {
                            Optional<Disciplina> disciplina = this.buscaDisciplina(scanner);

                            if(disciplina.isPresent()) {
                                aluno.getMatricula().removerDisciplina(disciplina.get());
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Opção desconhecida! Assumindo sair.");
                    opcao = 0;
                    break;
            }

            this.menuAtualizacaoCadastros(OPCAO_MENU_ALUNO);
        }
    }

    public void criarAluno(Scanner scanner) {
        System.out.println("Digite o nome do Aluno(a):");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha do Aluno(a):");
        String senha = scanner.nextLine();

        Optional<Aluno> alunoExiste = this.alunos.stream().filter(a -> a.getNome().equals(nome)).findFirst();

        if (alunoExiste.isPresent()) {
            System.out.println("Já existe um aluno com este nome!");
        } else {
            this.alunos.add(new Aluno(nome, senha));
            System.out.println("\n\nAluno(a) adicionado(a)!");
        }
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

    public void visualizarTodosOsAlunos() {
        this.alunos.stream().forEach((aluno) -> {
            System.out.println(aluno);
        });
    }

    // #endregion

    // #region professor

    public void atualizarProfessor(Professor professor, Scanner scanner) {
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
                            Optional<Disciplina> disciplina = this.buscaDisciplina(scanner);

                            if (disciplina.isPresent()) {
                                professor.adicionarDisciplina(disciplina.get());
                                System.out.println("Disciplina adicionada com sucesso!");
                            } else {
                                System.out.println("Disciplina não encontrada!");
                            }
                        } else if (opcao2 == 2) {
                            Optional<Disciplina> disciplina = this.buscaDisciplina(scanner);
                            
                            if(disciplina.isPresent()) {
                                professor.removerDisciplina(disciplina.get());
                            } 
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

    public void criarProfessor(Scanner scanner) {

        System.out.println("Digite o nome do Professor:");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha do Professor:");
        String senha = scanner.nextLine();

        Optional<Professor> professorExiste = this.professores.stream().filter(p -> p.getNome().equals(nome)).findFirst();

        if(professorExiste.isPresent()) {
            System.out.println("Já existe um professor com este nome!");
        } else {
            System.out.println("Deseja adicionar disciplinas para este novo professor?");
            System.out.println("1 - Sim | 2 - Não");
            int opcao = scanner.nextInt();
    
            ArrayList<Disciplina> discAdd = new ArrayList<Disciplina>();
            if (opcao == 1) {
                int opcao2 = 1;
                while (opcao2 == 1) {
                    Optional<Disciplina> disciplina = this.buscaDisciplina(scanner);
    
                    if (disciplina.isPresent()) {
                        discAdd.add(disciplina.get());
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
            System.out.println("\n\nProfessor(a) adicionado(a)!");
        }
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

    public void visualizarTodosOsProfessores() {
        this.professores.stream().forEach((professor) -> {
            System.out.println(professor);
        });
    }

    // #endregion

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
        }

        // Bloco comum final
        System.out.println("0 - Sair");
    }

    public void menuAtualizacaoDisciplina() {
        System.out.println("Qual característica da disciplina será modificada?");
        System.out.println("1 - Nome da disciplina.");
        System.out.println("2 - Obrigatoriedade da disciplina.");
        System.out.println("3 - Verificar quantidade de alunos da disciplina.");
        System.out.println("4 - Atualizar alunos da disciplina.");
        System.out.println("0 - Sair");
    }

    public Optional<Disciplina> buscaDisciplina(Scanner scanner) {
        System.out.println("Digite o nome da disciplina:");
        String nomeDisciplina = scanner.nextLine();

        return this.disciplinas.stream().filter(d -> d.getNome().equals(nomeDisciplina)).findFirst();

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
                    menuDisciplina(teclado);
                    break;
                case 2:
                    menuProfessor(teclado);
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

    private void menuProfessor(Scanner teclado) {
        int opcao;
        do {
            opcao = opcoesCRUD(teclado, OpcoesSecretaria.OPCAO_MENU_PROFESSOR);
            Professor prof;

            switch (opcao) {
                case 1:
                    prof = getProfessorByNome(teclado);
                    if (prof != null)
                        visualizarProfessor(prof);
                    break;
                case 2:
                    criarProfessor(teclado);
                    break;
                case 3:
                    prof = getProfessorByNome(teclado);
                    if (prof != null)
                        atualizarProfessor(prof, teclado);
                    break;
                case 4:
                    prof = getProfessorByNome(teclado);
                    excluirProfessor(prof);
                    break;
                case 5:
                    visualizarTodosOsProfessores();
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);
    }

    private void menuAluno(Scanner teclado) {
        int opcao;
        Aluno a;
        do {
            opcao = opcoesCRUD(teclado, OpcoesSecretaria.OPCAO_MENU_ALUNO);

            switch (opcao) {
                case 1:
                    a = getAlunoByNome(teclado);
                    if (a != null)
                        visualizarAluno(a);
                    break;
                case 2:
                    criarAluno(teclado);
                    break;

                case 3:
                    a = getAlunoByNome(teclado);
                    if (a != null)
                        atualizarAluno(a, teclado);
                    break;
                case 4:
                    a = getAlunoByNome(teclado);
                    excluirAluno(a);
                    break;
                case 5:
                    visualizarTodosOsAlunos();
                    break;

            }
            pausa(teclado);
            limparTela();
        } while (opcao != 0);
    }

    private void menuDisciplina(Scanner teclado) {
        int opcao;
        Disciplina disciplina;
        do {
            opcao = opcoesCRUD(teclado, OpcoesSecretaria.OPCAO_MENU_DISCIPLINA);
            switch (opcao) {
                case 1:
                    disciplina = getDisciplinaByNome(teclado);
                    if (disciplina != null)
                        visualizarDisciplina(disciplina);
                    break;
                case 2:
                    criarDisciplina(teclado);
                    break;
                case 3:
                    disciplina = getDisciplinaByNome(teclado);
                    if (disciplina != null)
                        atualizarDisciplina(disciplina, teclado);
                    break;
                case 4:
                    disciplina = getDisciplinaByNome(teclado);
                    excluirDisciplina(disciplina);
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

    // #endregion

    // #region pesquisa por nome
    private Aluno getAlunoByNome(Scanner teclado) {
        Optional<Aluno> aluno;
        boolean erro = false;

        do {
            System.out.println("Digite o nome. Deixe em branco para sair");
            String nome = teclado.nextLine();
            aluno = alunos.stream().filter(a -> a.getNome().equals(nome)).findAny();
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

    private Professor getProfessorByNome(Scanner teclado) {
        Optional<Professor> prof;
        boolean erro = false;

        do {
            System.out.println("Digite o nome. Deixe em branco para sair");
            String nome = teclado.nextLine();
            prof = professores.stream().filter(a -> a.getNome().equals(nome)).findAny();
            if (prof.isEmpty() && !nome.equals("")) {
                erro = true;
                System.out.println("Esse professor não existe! Tente novamente.");
            } else
                erro = false;
        } while (erro);

        if (prof.isPresent()) {
            return prof.get();
        }
        return null;
    }

    private Disciplina getDisciplinaByNome(Scanner teclado) {
        Optional<Disciplina> disciplina;
        boolean erro = false;

        do {
            System.out.println("Digite o nome. Deixe em branco para sair");
            String nome = teclado.nextLine();
            disciplina = disciplinas.stream().filter(a -> a.getNome().equals(nome)).findAny();
            if (disciplina.isEmpty() && !nome.equals("")) {
                erro = true;
                System.out.println("Essa disciplina não existe! Tente novamente.");
            } else
                erro = false;
        } while (erro);

        if (disciplina.isPresent()) {
            return disciplina.get();
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
