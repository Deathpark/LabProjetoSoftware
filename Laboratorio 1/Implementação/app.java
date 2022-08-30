import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // inicializar o programa:
        Secretaria s = Secretaria.getInstance();

        Usuario usuarioAtual = menuLogin(teclado);
        System.out.println(usuarioAtual);

        // teste
        // System.out.println("Aluno teste:");
        // String nome = teclado.nextLine();
        // s.criarAluno(nome, "1234");

        // System.out.println("\nAlunos salvos:");
        // s.imprimirAlunos();

        // quando encerrar o programa:
        s.salvar();
        teclado.close();
    }

    // #region Métodos do MENU
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    public static int menu(Scanner teclado) {
        limparTela();
        System.out.println("SISTEMA");
        System.out.println("Selecione o tipo de usuário:");
        System.out.println("1 - Secretaria");
        System.out.println("2 - Professor");
        System.out.println("3 - Aluno");

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

    public static Usuario menuLogin(Scanner teclado) {
        Secretaria s = Secretaria.getInstance();
        ArrayList<Usuario> usuarios = s.getUsuarios();

        System.out.println("=====Login=====");
        Optional<Usuario> usuario;
        boolean erro = false;

        do {
            System.out.println("Digite seu nome. Deixe em branco para sair");
            String nome = teclado.nextLine();
            usuario = usuarios.stream().filter(u -> u.nome.equals(nome)).findAny();
            if (usuario.isEmpty() && !nome.equals("")) {
                erro = true;
                System.out.println("Esse usuário não existe! Tente novamente.");
            } else
                erro = false;
        } while (erro);

        if (usuario.isPresent()) {
            do {
                System.out.println("Digite a senha");
                String senha = teclado.nextLine();

                if (usuario.get().login(senha)) {
                    erro = false;
                    System.out.println("Usuário logado");

                } else {
                    erro = true;
                    System.out.println("Senha incorreta! Tente novamente.");
                }

            } while (erro);
            return usuario.get();
        }
        return null;
    }
}
