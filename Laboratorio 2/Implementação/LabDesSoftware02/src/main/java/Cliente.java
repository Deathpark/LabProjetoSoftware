
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

@Entity
@Table(name = "cliente")
public class Cliente {
    private String rg;
    private int cpf;
    private Endereco endereco;
    private String profissao;
    private ArrayList<Empresa> rendimentos;
    
    public String getRg() {
        return rg;
    }
    
    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public int getCpf() {
        return cpf;
    }
    
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getProfissao() {
        return profissao;
    }
    
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    
    public ArrayList<Empresa> getRendimentos() {
        return rendimentos;
    }
    
    public void setRendimentos(ArrayList<Empresa> rendimentos) {
        this.rendimentos = rendimentos;
    }

    public void criarPedido() {
        
    }

    public void atualizarPedido(int pedido) {
        
    }
    
    public void consultarPedido(int pedido) {
        
    }

    public void cancelarPedido(int pedido) {
        
    }
}
