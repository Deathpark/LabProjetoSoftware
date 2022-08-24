import java.util.ArrayList;

public class Secretaria extends Usuario {
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Disciplina> disciplinas;

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

    }

    public void criarProfessor() {

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
        System.out.println("Qual característica do(a) " + opcao + " será modificada?");
        System.out.println("1 - Nome do(a) " + opcao + ".");
        System.out.println("2 - Senha do(a) " + opcao + ".");

        if(opcao.equals("aluno(a)")) {
            System.out.println("3 - Matricula atual do(a) " + opcao + ".");
        } else if(opcao.equals("professor(a)")) {
            System.out.println("3 - Disciplinas que o(a) " + opcao + " leciona.");
        } else if(opcao.equals("disciplina")) {
            System.out.println("3 - Nome da " + opcao);
        }
    }
}
