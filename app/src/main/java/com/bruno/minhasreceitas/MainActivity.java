package com.bruno.minhasreceitas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.utils.RetrofitConfig;
import com.bruno.minhasreceitas.view.CreateActivity;
import com.bruno.minhasreceitas.view.adapter.CategoriasAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CategoriasAdapter.OnClickListener{
    private RecyclerView mRecyclerV;
    private FloatingActionButton mAddButton;
    private CategoriasAdapter mCategoriaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerV = findViewById(R.id.mRecyclerV);
        mAddButton = findViewById(R.id.mAddButton);
        mRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        mCategoriaAdapter = new CategoriasAdapter(this);
        mRecyclerV.setAdapter(mCategoriaAdapter);

        carregarCategorias();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarCategorias();
    }

    @Override
    public void onClickItem(Categoria categoria) {
        Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();
        /*Intent intent = new Intent(MainActivity.this, AtualizarListaEsperaActiviy.class);

        intent.putExtra(AtualizarListaEsperaActiviy.EXTRA_LISTA_ESPERA, listaEspera);

        startActivity(intent);*/
    }

    public void onClickAddButton(View v) {
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
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
