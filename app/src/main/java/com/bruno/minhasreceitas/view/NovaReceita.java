package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bruno.minhasreceitas.MainActivity;
import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.model.Receita;
import com.bruno.minhasreceitas.utils.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovaReceita extends AppCompatActivity {

    private int categoriaId;
    private TextView categoriaText;
    private EditText titulo, ingredientes, comoFazer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);

        categoriaText = findViewById(R.id.categoriaText);
        titulo = findViewById(R.id.titulo);
        ingredientes = findViewById(R.id.ingredientes);
        comoFazer = findViewById(R.id.como_fazer);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if(savedInstanceState == null) {
            if(bundle != null) {
                categoriaId = (Integer) bundle.get("CATEGORIA_ID");
            }

            else {
                categoriaId = 0;

            }
        }

        else {
            categoriaId = (Integer) savedInstanceState.getSerializable("CATEGORIA_ID");
        }

        carregarCategoria(categoriaId);

    }

    private void carregarCategoria(int categoriaId) {
        Call<List<Categoria>> call = new RetrofitConfig().getCategoriaService().getCategoria(String.valueOf(categoriaId));

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                List<Categoria> categoria = response.body();
                categoriaText.setText("Categoria: " + categoria.get(0).getNome());
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar categoria!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void salvarReceita(View view) {
        Receita receita = new Receita();

        receita.setTitulo(titulo.getText().toString());
        receita.setIngredientes(ingredientes.getText().toString());
        receita.setComo_fazer(comoFazer.getText().toString());
        receita.setCategoria_id(this.categoriaId);

        Call<Receita> call = new RetrofitConfig().getReceitaService().novaReceita(receita);

        call.enqueue(new Callback<Receita>() {
            @Override
            public void onResponse(Call<Receita> call, Response<Receita> response) {
                Toast.makeText(getApplicationContext(), "Receita salva com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Receita> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao salvar nova rceita!", Toast.LENGTH_LONG).show();
                finish();
                Log.e("CategoriaService ", "Erro ao salvar nova receita " + t.getMessage());
            }
        });
    }
}
