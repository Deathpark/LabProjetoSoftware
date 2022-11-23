package com.lab.sistemaestudantil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String nomeRemetente;
    @Column(nullable = false)
    private String nomeDestinatario;
    @Column(nullable = false)
    private int quantidade;
    private String tipoRemetente;

    public Historico(long idRemetente, String nomeRemetente, long idDestinatario, String nomeDestinatario, int quantidade, String tipoRemetente) {
        this.idRemetente = idRemetente;
        this.nomeRemetente = nomeRemetente;
        this.idDestinatario = idDestinatario;
        this.nomeDestinatario = nomeDestinatario;
        this.quantidade = quantidade;
        this.tipoRemetente = tipoRemetente;
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

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public long getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(long idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoRemetente() {
        return tipoRemetente;
    }

    public void setTipoRemetente(String tipoRemetente) {
        this.tipoRemetente = tipoRemetente;
    }

    
}
