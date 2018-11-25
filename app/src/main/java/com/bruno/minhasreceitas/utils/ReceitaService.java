package com.bruno.minhasreceitas.utils;

import com.bruno.minhasreceitas.model.Receita;

import java.util.List;

import retrofit2.*;
import retrofit2.http.*;

public interface ReceitaService {

    @POST("receitas")
    Call<Receita> novaReceita(@Body Receita receita);

    @GET("receitas")
    Call<List<Receita>> getReceitas();

    @GET("receitas/{id}")
    Call<List<Receita>> getReceita(@Path("id") String id);

    @GET("categorias/receitas/{id}")
    Call<List<Receita>> getReceitas(@Path("id") String id);

    @DELETE("receitas/{id}")
    Call<Void> deleteReceita(@Path("id") String id);

    @PUT("receitas")
    Call<Void> editarReceita(@Body Receita receita);
}
