package com.bruno.minhasreceitas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receita {

    private int id;

    private int categoria_id;

    private String titulo, ingredientes;

    private String como_fazer;

    public Receita() {
        super();
    }

    public Receita(int id, int categoria_id, String titulo, String ingredientes, String como_fazer) {
        this.id = id;
        this.categoria_id = categoria_id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.como_fazer = como_fazer;
    }

    public Receita(int categoria_id, String titulo, String ingredientes, String como_fazer) {
        this.categoria_id = categoria_id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.como_fazer = como_fazer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getComo_fazer() {
        return como_fazer;
    }

    public void setComo_fazer(String como_fazer) {
        this.como_fazer = como_fazer;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
}
