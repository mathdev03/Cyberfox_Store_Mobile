package com.matheus.cyberfox_store.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id = 0;
    private String usuario = "";
    private String email = "";
    private String senha = "";
    private int imagem = 0;

    public Usuario(int id, String usuario, String email, String senha, int imagem) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
    }

    public Usuario(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
