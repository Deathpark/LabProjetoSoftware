import java.io.Serializable;
import java.util.Scanner;

public abstract class Usuario implements Serializable {
    protected String nome;
    protected String senha;
    static final long serialVersionUID = 9;

    protected Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public boolean login(String senha) {
        return senha.equals(this.senha);
    }

    public String getNome() {
        return this.nome;
    }

    public void logout() {
        System.out.println("Você está deslogado.");
    }

    @Override
    public String toString() {
        return nome;
    }

    //#region menu
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa(Scanner teclado) {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    abstract public void menu(Scanner teclado);
    //#endregion
}
