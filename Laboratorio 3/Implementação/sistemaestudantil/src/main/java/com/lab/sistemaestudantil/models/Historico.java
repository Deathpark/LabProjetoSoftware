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
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private long idRemetente;
    @Column(nullable = false)
    private long idDestinatario;
    @Column(nullable = false)
    private int quantidade;

    public Historico(long idRemetente, long idDestinatario, int quantidade) {
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.quantidade = quantidade;
    }

    public Historico() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(long idRemetente) {
        this.idRemetente = idRemetente;
    }

    public long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
