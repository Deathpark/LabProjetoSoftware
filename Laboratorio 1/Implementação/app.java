import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // inicializar o programa:
        Secretaria s = new Secretaria();

        // teste
        System.out.println("Aluno teste:");
        String nome = teclado.nextLine();
        s.criarAluno(nome, "1234");

        System.out.println("\nAlunos salvos:");
        s.imprimirAlunos();

        // quando encerrar o programa: 
        s.salvar();
        teclado.close();
    }
}
