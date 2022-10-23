package com.lab.sistemaestudantil.models;

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
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    private Vantagem[] vantagens;

    public Empresa(String nome, String senha, Vantagem[] vantagens) {
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

    public Vantagem[] getVantagens() {
        return vantagens;
    }

    public void setVantagens(Vantagem[] vantagens) {
        this.vantagens = vantagens;
    }

    public void criarVantagem() {

    }

    public void atualizarVantagem(int vantagem) {

    }

    public void consultarVantagem(int vantagem) {

    }

    public void cancelarVantagem(int vantagem) {

    }
}
