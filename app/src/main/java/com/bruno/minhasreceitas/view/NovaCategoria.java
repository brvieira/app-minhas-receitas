package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bruno.minhasreceitas.MainActivity;
import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.utils.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovaCategoria extends AppCompatActivity {

    private EditText nomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_categoria);
        nomeText = findViewById(R.id.categoria_nome);
    }

    public void salvarCategoria(View view) {
        Categoria categoria = new Categoria(String.valueOf(nomeText.getText()));

        Call<Categoria> call = new RetrofitConfig().getCategoriaService().newCategoria(categoria);

        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                Toast.makeText(getApplicationContext(), "Categoria salva com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NovaCategoria.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao salvar nova categoria!", Toast.LENGTH_LONG).show();
                finish();
                Log.e("CategoriaService ", "Erro ao salvar nova Categoria " + t.getMessage());
            }
        });

    }

}
