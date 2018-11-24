package com.bruno.minhasreceitas.utils;

import com.bruno.minhasreceitas.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoriaService {

    @GET("categorias")
    Call<List<Categoria>> getCategorias();

    @GET("categorias/{id}")
    Call<List<Categoria>> getCategoria(@Path("id") String id);

    @POST("categorias")
    Call<Categoria> newCategoria(@Body Categoria categoria);
}
