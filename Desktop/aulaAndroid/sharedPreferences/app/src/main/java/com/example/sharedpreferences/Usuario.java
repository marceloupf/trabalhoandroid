package com.example.sharedpreferences;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer codusuario;
    private String nome;
    private String email;
    private String tpusuario;


    public Integer getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Integer codusuario) {
        this.codusuario = codusuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTpusuario() {
        return tpusuario;
    }

    public void setTpusuario(String tpusuario) {
        this.tpusuario = tpusuario;
    }



    @Override
    public String toString(){
        return nome;
    }
}
