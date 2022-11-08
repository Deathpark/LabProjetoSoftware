package com.lab.sistemaestudantil.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private String cpf;
    private String rg;
    private String endereco;
    private String email;
    private String instituicaoEnsino;
    private String curso;
    private int moedas;
    private String[] vantagens;
    private ArrayList<Integer> historico;

    public Aluno(String nome, String senha, String cpf, String rg, String endereco, String email,
            String instituicaoEnsino, String curso) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.email = email;
        this.instituicaoEnsino = instituicaoEnsino;
        this.curso = curso;
        this.moedas = 0;
        this.vantagens = new String[] {};
        this.historico = new ArrayList<Integer>();
    }

    public Aluno() {
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        int diferenca = this.moedas + moedas;
        this.moedas = moedas;

        if(this.historico == null) {
            this.historico = new ArrayList<Integer>();
        }
        
        this.historico.add(diferenca);
    }

    public String[] getVantagens() {
        return vantagens;
    }

    public void setVantagens(String[] vantagens) {
        this.vantagens = vantagens;
    }
    
    public ArrayList<Integer> getHistorico() {
        return this.historico;
    }

    public void setHistorico(ArrayList<Integer> historico) {
        this.historico = historico;
    }

    @Override
    public String toString() {
        return "Aluno ID: " + this.id + "\nNome: " + nome;
    }
    

}
