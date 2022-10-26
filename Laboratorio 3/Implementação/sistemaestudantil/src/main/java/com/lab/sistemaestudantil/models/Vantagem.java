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
public class Vantagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private double valorVantagem;
    private int custo;
    private Empresa empresa;

    public Vantagem(String descricao, double valorVantagem, int custo, Empresa empresa, String endereco,
            String profissao,
            double[] rendimentos) {
        this.descricao = descricao;
        this.valorVantagem = valorVantagem;
        this.custo = custo;
        this.empresa = empresa;
    }

    public Vantagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
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
