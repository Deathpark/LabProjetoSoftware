import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nome;
    protected String senha;

    protected Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    public void login() {

    }

    public void logout() {

    }

    @Override
    public String toString() {
        return nome;
    }
}
