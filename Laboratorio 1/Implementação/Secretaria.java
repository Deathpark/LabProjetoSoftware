import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Secretaria extends Usuario {
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Disciplina> disciplinas;
    private String ARQ_ALUNOS = "./arquivos/alunos.bin";
    private String ARQ_PROFESSORES = "./arquivos/professores.bin";
    private String ARQ_DISCIPLINAS = "./arquivos/disciplinas.bin";

    public Secretaria() {
        super("nome", "senha");
        this.alunos = lerDados(ARQ_ALUNOS);
        this.disciplinas = lerDados(ARQ_DISCIPLINAS);
        this.professores = lerDados(ARQ_PROFESSORES);
    }

    public void salvar() {
        escreverDados(alunos, ARQ_ALUNOS);
        escreverDados(professores, ARQ_PROFESSORES);
        escreverDados(disciplinas, ARQ_DISCIPLINAS);
    }

    public void imprimirAlunos() {
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

    }

    public void criarProfessor() {

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
        System.out.println("Qual característica do(a) " + opcao + " será modificada?");
        System.out.println("1 - Nome do(a) " + opcao + ".");
        System.out.println("2 - Senha do(a) " + opcao + ".");

        if (opcao.equals("aluno(a)")) {
            System.out.println("3 - Matricula atual do(a) " + opcao + ".");
        } else if (opcao.equals("professor(a)")) {
            System.out.println("3 - Disciplinas que o(a) " + opcao + " leciona.");
        } else if (opcao.equals("disciplina")) {
            System.out.println("3 - Nome da " + opcao);
        }
    }

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
}
