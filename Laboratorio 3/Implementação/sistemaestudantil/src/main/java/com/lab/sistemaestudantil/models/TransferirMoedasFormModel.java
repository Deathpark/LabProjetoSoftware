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
public class TransferirMoedasFormModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qntMoedas;
    private long professorId;
    private long alunoId;

    public TransferirMoedasFormModel(int qntMoedas, long professorId, long alunoId) {
        this.qntMoedas = qntMoedas;
        this.professorId = professorId;
        this.alunoId = alunoId;
    }

    public TransferirMoedasFormModel() {
    }

    public int getQntMoedas() {
        return this.qntMoedas;
    }

    public void setQntMoedas(int qntMoedas) {
        this.qntMoedas = qntMoedas;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    @Override
    public String toString() {
        return "Moedas: " + this.qntMoedas + "\nProfessor ID: " + professorId + "\nAluno ID: " + alunoId;
    }
}
