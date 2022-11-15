package com.lab.sistemaestudantil.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private String cpf;
    private String instituicaoEnsino;
    private int moedas;
    private ArrayList<Integer> historico;

    public Professor(String nome, String senha, String cpf, String instituicaoEnsino, int moedas) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.instituicaoEnsino = instituicaoEnsino;
        this.moedas = 0;
        this.historico = new ArrayList<Integer>();
    }

    public Professor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public ArrayList<Integer> getHistorico() {
        return this.historico;
    }

    public void setHistorico(ArrayList<Integer> historico) {
        this.historico = historico;
    }

    public int transferirMoedas(int quantidade) {
        int moedasAtuais = this.getMoedas();
        
        moedasAtuais -= quantidade;
        this.setMoedas(moedasAtuais);
        this.adicionarHistorico(quantidade, false);
        return quantidade;

    }

    public void adicionarHistorico(int quantidade, boolean adicao) {
        if(adicao) {
            if(this.historico == null) {
                this.historico = new ArrayList<Integer>();
            }
    
            this.historico.add(quantidade);
        } else {
            if(this.historico == null) {
                this.historico = new ArrayList<Integer>();
            }
    
            this.historico.add((quantidade*-1));
        }
    }

    public void moedasMensais() {
        this.setMoedas(this.getMoedas()+1000);

        if(this.historico == null) {
            this.historico = new ArrayList<Integer>();
        }

        this.historico.add(1000);
    }

    public boolean consultarMoedas(int quantidade) {
        if(this.getMoedas() >= quantidade) {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Professor ID: " + this.id + "\nNome: " + nome;
    }
}
