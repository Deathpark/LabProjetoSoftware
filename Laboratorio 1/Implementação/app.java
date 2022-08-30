import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // inicializar o programa:
        Secretaria s = Secretaria.getInstance();
        // login
        Usuario usuarioAtual = menuLogin(teclado);
        System.out.println(usuarioAtual);
        usuarioAtual.menu(teclado);

        // teste
        // System.out.println("Aluno teste:");
        // String nome = teclado.nextLine();
        // s.criarAluno(nome, "1234");

        // System.out.println("\nAlunos salvos:");
        // s.imprimirAlunos();

        // quando encerrar o programa:
        s.salvar();
        usuarioAtual.logout();
        teclado.close();
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
