package com.lab.sistemaestudantil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ComprarVantagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qntMoedas;
    private long vantagemId;

    public ComprarVantagemModel(int qntMoedas, long vantagemId) {
        this.qntMoedas = qntMoedas;
        this.vantagemId = vantagemId;
    }

    public ComprarVantagemModel() {
    }

    public int getQntMoedas() {
        return this.qntMoedas;
    }

    public void setQntMoedas(int qntMoedas) {
        this.qntMoedas = qntMoedas;
    }

    public long getVantagemId() {
        return vantagemId;
    }

    public void setVantagemId(long vantagemId) {
        this.vantagemId = vantagemId;
    }

}
