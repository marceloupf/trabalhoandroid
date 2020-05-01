package com.example.sharedpreferences;

import java.io.Serializable;

public class Evento implements Serializable {
    private Integer codevento;
    private String nome;
    private String descricao;
    private String data;
    private String valor;
    private String numvagas;
    private String local;

    public Integer getCodevento() {
        return codevento;
    }

    public void setCodevento(Integer codevento) {
        this.codevento = codevento;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNumvagas() {
        return numvagas;
    }

    public void setNumvagas(String numvagas) {
        this.numvagas = numvagas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString(){
        return nome;
    }
}
