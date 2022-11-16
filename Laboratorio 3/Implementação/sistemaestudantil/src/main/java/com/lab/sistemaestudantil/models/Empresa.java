package com.lab.sistemaestudantil.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private ArrayList<Vantagem> vantagens;
    public Empresa(String nome, String senha, ArrayList<Vantagem> vantagens) {
        this.nome = nome;
        this.senha = senha;
        this.vantagens = vantagens;
    }

    public Empresa() {
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

    public ArrayList<Vantagem> getVantagens() {
        return vantagens;
    }

    public void setVantagens(ArrayList<Vantagem> vantagens) {
        this.vantagens = vantagens;
    }
}
