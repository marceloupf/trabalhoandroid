package com.example.sharedpreferences;

import java.io.Serializable;

public class Inscricao implements Serializable {

    private Integer codInscricao;
    private Integer codevento;
    private Integer codusuario;

    public Integer getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(Integer codInscricao) {
        this.codInscricao = codInscricao;
    }

    public Integer getCodevento() {
        return codevento;
    }

    public void setCodevento(Integer codevento) {
        this.codevento = codevento;
    }

    public Integer getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Integer codusuario) {
        this.codusuario = codusuario;
    }

}
