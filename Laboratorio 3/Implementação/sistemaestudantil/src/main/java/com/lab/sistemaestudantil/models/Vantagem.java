package com.lab.sistemaestudantil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vantagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private double valorVantagem;
    private int custoVantagem;
    private long idEmpresa;
    private String foto;
    private String descricao;

    public Vantagem(String nome, double valorVantagem, int custoVantagem, long idEmpresa, String endereco,
            String profissao, String foto, String descricao) {
        this.descricao = descricao;
        this.valorVantagem = valorVantagem;
        this.custoVantagem = custoVantagem;
        this.idEmpresa = idEmpresa;
        this.foto = foto;
        this.descricao = descricao;
    }

    public Vantagem() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorVantagem() {
        return valorVantagem;
    }

    public void setValorVantagem(double valorVantagem) {
        this.valorVantagem = valorVantagem;
    }

    public int getCustoVantagem() {
        return custoVantagem;
    }

    public void setCustoVantagem(int custoVantagem) {
        this.custoVantagem = custoVantagem;
    }

    public void setEmpresaId(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public long getEmpresaId() {
        return idEmpresa;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

}
