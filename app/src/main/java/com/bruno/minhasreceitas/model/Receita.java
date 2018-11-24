package com.bruno.minhasreceitas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receita {

    private int id;

    @JsonProperty("categoria_id")
    private int categoriaId;

    private String titulo, ingredientes;

    @JsonProperty("como_fazer")
    private String comoFazer;

    public Receita() {
        super();
    }

    public Receita(int id, int categoriaId, String titulo, String ingredientes, String comoFazer) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.comoFazer = comoFazer;
    }

    public Receita(int categoriaId, String titulo, String ingredientes, String comoFazer) {
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.comoFazer = comoFazer;
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

    public String getComoFazer() {
        return comoFazer;
    }

    public void setComoFazer(String comoFazer) {
        this.comoFazer = comoFazer;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
}
