import java.io.Serializable;

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

    public void logout() {

    }

    @Override
    public String toString() {
        return nome;
    }
}
