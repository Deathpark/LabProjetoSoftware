import java.util.ArrayList;
import java.util.Scanner;

public class Secretaria extends Usuario {
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Disciplina> disciplinas;
    private static final String OPCAO_MENU_PROFESSOR = "professor(a)";
    private static final String OPCAO_MENU_ALUNO = "aluno(a)";
    private static final String OPCAO_MENU_DISCIPLINA = "disciplina";

    public Secretaria() {

    }

    public Secretaria(ArrayList<Aluno> alunos, ArrayList<Professor> professores, ArrayList<Disciplina> disciplinas) {

    }

    public void gerarCurriculoSemestre() {

    }

    public Disciplina criarDisciplina() {
        return new Disciplina();
    }

    public void atualizarDisciplina(Disciplina disciplina) {

    }

    public void visualizarDisciplina(Disciplina disciplina) {
        System.out.println(disciplina.toString());
    }

    public void excluirDisciplina(Disciplina disciplina) {
        boolean removeu = this.disciplinas.removeIf(d -> d.equals(disciplina));

        if(removeu) {
            System.out.println("Disciplina removida com sucesso!");
        } else {
            System.out.println("Disciplina não encontrada!");
        }
    }

    public void atualizarAluno(Aluno aluno){
        int resposta = 1;
        while(resposta > 0) {
        }
        Aluno alunoAux = this.alunos.stream().filter(a -> a.equals(aluno)).findFirst().get();    
    }

    public void criarAluno() {

    }

    public void excluirAluno(Aluno aluno) {
        boolean removeu = this.alunos.removeIf(a -> a.equals(aluno));

        if(removeu) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public void visualizarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
    }

    public void atualizarProfessor(Professor professor){
        Scanner scanner = new Scanner(System.in);
        
        this.menuAtualizacaoCadastros(OPCAO_MENU_PROFESSOR);
        int opcao = scanner.nextInt();
        
        while(opcao != 0) {
            switch(opcao) {
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
                    while(opcao2 == 1 || opcao2 == 2) {
                        System.out.println("1 - Adicionar disciplinas | 2 - Remover disciplinas");
                        System.out.println("Digite qualquer outro número para finalizar.");
                        opcao2 = scanner.nextInt();
                        
                        if(opcao2 == 1) {
                            Disciplina disciplina = this.buscaDisciplina(scanner);
                            
                            if(disciplina != null) {
                                professor.adicionarDisciplina(disciplina);
                                System.out.println("Disciplina adicionada com sucesso!");
                            } else {
                                System.out.println("Disciplina não encontrada!");
                            }
                        } else if(opcao2 == 2) {
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
        if(opcao == 1) {
            int opcao2 = 1;
            while(opcao2 == 1) {
                Disciplina disciplina = this.buscaDisciplina(scanner);

                if(disciplina != null) {
                    discAdd.add(disciplina);
                    System.out.println("Disciplina adicionada!");
                } else {
                    System.out.println("Disicplina não encontrada!");
                }

                System.out.println("\nDeseja adicionar mais disciplinas?");
                opcao2 = scanner.nextInt();
            }
        } else if(opcao != 2){
            System.out.println("Opção desconhecida! Assumindo não.");
        }
        
        this.professores.add(new Professor(nome, senha, disciplinas));
        System.out.println("\n\nProfessor adicionado!");

        scanner.close();
    }

    public void excluirProfessor(Professor professor) {
        boolean removeu = this.professores.removeIf(p -> p.equals(professor));

        if(removeu) {
            System.out.println("Professor removido com sucesso!");
        } else {
            System.out.println("Professor não encontrado!");
        }
    }

    public void visualizarProfessor(Professor professor) {
        System.out.println(professor.toString());
    }

    public void menuAtualizacaoCadastros(String opcao) {
        //Bloco comum
        System.out.println("Qual característica do(a) " + opcao + " será modificada?");
        System.out.println("1 - Nome do(a) " + opcao + ".");
        System.out.println("2 - Senha do(a) " + opcao + ".");

        //Bloco específico
        if(opcao.equals("aluno(a)")) {
            System.out.println("3 - Matricula atual do(a) " + opcao + ".");
        } else if(opcao.equals("professor(a)")) {
            System.out.println("3 - Disciplinas que o(a) " + opcao + " leciona.");
        } else if(opcao.equals("disciplina")) {
            System.out.println("3 - Nome da " + opcao);
        }

        //Bloco comum final
        System.out.println("0 - Sair");
    }

    public Disciplina buscaDisciplina(Scanner scanner) {
        System.out.println("Digite o nome da disciplina:");
        String nomeDisciplina = scanner.nextLine();

        return this.disciplinas.stream().filter(d -> d.getNome().equals(nomeDisciplina)).findFirst().get();

    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        
    }
}
