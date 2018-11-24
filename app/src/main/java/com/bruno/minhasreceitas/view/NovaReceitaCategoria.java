package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.utils.RetrofitConfig;
import com.bruno.minhasreceitas.view.adapter.CategoriasAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovaReceitaCategoria extends AppCompatActivity implements CategoriasAdapter.OnClickListener{
    private RecyclerView mRecyclerV;
    private CategoriasAdapter mCategoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita_categoria);
        mRecyclerV = findViewById(R.id.mRecyclerV);
        mRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        mCategoriaAdapter = new CategoriasAdapter(this);
        mRecyclerV.setAdapter(mCategoriaAdapter);
        carregarCategorias();
    }

    @Override
    public void onClickItem(Categoria categoria) {
        Intent intent = new Intent(NovaReceitaCategoria.this, NovaReceita.class);
        intent.putExtra("CATEGORIA_ID", categoria.getId());
        startActivity(intent);
    }

    public void carregarCategorias() {
        Call<List<Categoria>> call = new RetrofitConfig().getCategoriaService().getCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                mCategoriaAdapter.setListaCategorias(response.body());
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar categorias!", Toast.LENGTH_LONG).show();
                carregarCategorias();
                Log.e("CategoriaService ", "Erro ao buscar Categorias "+ t.getMessage());
            }
        });
    }
}
