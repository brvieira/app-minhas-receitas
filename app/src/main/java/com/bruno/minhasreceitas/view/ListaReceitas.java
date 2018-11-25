package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Receita;
import com.bruno.minhasreceitas.utils.RetrofitConfig;
import com.bruno.minhasreceitas.view.adapter.ReceitaAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaReceitas extends AppCompatActivity implements ReceitaAdapter.OnClickListener{

    private RecyclerView mRecyclerV;
    private ReceitaAdapter adapter;
    private int categoriaId;
    private String categoriaNome;
    private TextView categoriaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_receitas);
        mRecyclerV = findViewById(R.id.mRecyclerV);
        mRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceitaAdapter(this);
        mRecyclerV.setAdapter(adapter);
        categoriaText = findViewById(R.id.categoria_nome);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if(savedInstanceState == null) {
            if(bundle != null) {
                categoriaId = (Integer) bundle.get("CATEGORIA_ID");
                categoriaNome = (String) bundle.get("CATEGORIA_NOME");
            }

            else {
                categoriaId = 0;
                categoriaNome = null;
            }
        }

        else {
            categoriaId = (Integer) savedInstanceState.getSerializable("CATEGORIA_ID");
            categoriaNome = (String) savedInstanceState.getSerializable("CATEGORIA_NOME");
        }

        categoriaText.setText(categoriaNome);
        carregarReceitas(categoriaId);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarReceitas(categoriaId);
    }

    @Override
    public void onClickItem(Receita receita) {
        Intent intent = new Intent(ListaReceitas.this, VerReceita.class);
        intent.putExtra("RECEITA_ID", receita.getId());
        startActivity(intent);
    }

    public void carregarReceitas(int categoriaId) {
        Call<List<Receita>> call = new RetrofitConfig().getReceitaService().getReceitas(String.valueOf(categoriaId));

        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                adapter.setListaReceitas(response.body());
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar receitas!", Toast.LENGTH_LONG).show();
                finish();
                Log.e("CategoriaService ", "Erro ao buscar receitas "+ t.getMessage());
            }
        });
    }
}
