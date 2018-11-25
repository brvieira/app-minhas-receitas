package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.model.Receita;
import com.bruno.minhasreceitas.utils.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerReceita extends AppCompatActivity {

    private int receitaId;
    private TextView mTitulo, mIngredientes, mComoFazer, mCategoria;
    private List<Receita> listaReceitas;
    private Receita receita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_receita);
        Intent intent = getIntent();

        mCategoria = findViewById(R.id.categoria_text);
        mTitulo = findViewById(R.id.titulo_text);
        mIngredientes = findViewById(R.id.ingredientes_text);
        mComoFazer = findViewById(R.id.modo_fazer_text);

        Bundle bundle = intent.getExtras();

        if(savedInstanceState == null) {
            if(bundle != null) {
                receitaId = (Integer) bundle.get("RECEITA_ID");
            }

            else {
                receitaId = 0;
            }
        }

        else {
            receitaId = (Integer) savedInstanceState.getSerializable("RECEITA_ID");
        }

        carregarReceita(receitaId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarReceita(receitaId);
    }

    private void carregarReceita(int receitaId) {
        Call<List<Receita>> call = new RetrofitConfig().getReceitaService().getReceita(String.valueOf(receitaId));

        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                List<Receita> lista = response.body();
                Receita receita = lista.get(0);
                mTitulo.setText(receita.getTitulo());
                mIngredientes.setText(receita.getIngredientes());
                mComoFazer.setText(receita.getComo_fazer());
                carregarCategoria(receita.getCategoria_id());
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar receita!", Toast.LENGTH_LONG).show();
                finish();
                Log.e("ReceitaService ", "Erro ao buscar receita "+ t.getMessage());
            }
        });
    }

    private void carregarCategoria(int categoriaId) {
        Call<List<Categoria>> call = new RetrofitConfig().getCategoriaService().getCategoria(String.valueOf(categoriaId));

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                List<Categoria> lista = response.body();
                Categoria categoria = lista.get(0);

                mCategoria.setText(categoria.getNome());
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar receita!", Toast.LENGTH_LONG).show();
                finish();
                Log.e("CategoriaService ", "Erro ao buscar receita "+ t.getMessage());
            }
        });
    }

    private void deletarReceita(int receitaId) {
        Call<Void> call = new RetrofitConfig().getReceitaService().deleteReceita(String.valueOf(receitaId));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao deletar receita!", Toast.LENGTH_LONG).show();
                Log.e("ReceitaService ", "Erro ao deletar receita "+ t.getMessage());
            }
        });
    }

    public void deletarReceita(View view) {
        deletarReceita(receitaId);
    }

    public void editarReceita(View view) {
        Intent intent = new Intent(VerReceita.this, EditarReceita.class);

        intent.putExtra("RECEITA_ID", receitaId);

        startActivity(intent);
    }
}
