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
    
    }

    public void atualizarAluno(Aluno aluno){

    }

    public void criarAluno() {

    }

    public void excluirAluno(Aluno aluno) {

    }

    public void visualizarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
    }

    public void atualizarProfessor(Professor professor){

    }

    public void criarProfessor() {

    }

    public void excluirProfessor(Professor professor) {

    }

    public void visualizarProfessor(Professor professor) {
        System.out.println(professor.toString());
    }
}
