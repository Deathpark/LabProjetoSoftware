package com.lab.aluguelveiculos.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private int cpf;
    private String rg;
    private String endereco;
    private String profissao;
    private ArrayList<Double> rendimentos;

    public Cliente(String nome, String senha, int cpf, String rg, String endereco, String profissao,
            ArrayList<Double> rendimentos) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.profissao = profissao;
        this.rendimentos = rendimentos;
    }

    public Cliente() {
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public ArrayList<Double> getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(ArrayList<Double> rendimentos) {
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
