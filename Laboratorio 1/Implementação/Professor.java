import java.util.ArrayList;
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

    public void verificarAlunos(Disciplina disciplina) {
        disciplina.getAlunos();
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

    public void login() {
        System.out.println("Digite seu nome: ");
        String nome = teclado.nextLine();
        
        System.out.println("Digite a senha: ");  
        String senha = teclado.nextLine();
        
        do {       
            if(nome == this.nome && senha == this.senha) {
                System.out.println("Logado com Sucesso!");       
            } else {      
                System.out.println("Usuário/Senha Incorreta");       
            }       
        } while(nome != this.nome && senha != this.senha) {
    }

    public void menu(Scanner teclado) {

    }
}
