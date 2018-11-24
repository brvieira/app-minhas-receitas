package com.bruno.minhasreceitas.utils;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.31:9090/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CategoriaService getCategoriaService() {
        return this.retrofit.create(CategoriaService.class);
    }

    public ReceitaService getReceitaService() {
        return this.retrofit.create(ReceitaService.class);
    }
}
