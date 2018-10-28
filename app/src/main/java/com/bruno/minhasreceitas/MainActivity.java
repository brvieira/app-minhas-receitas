package com.bruno.minhasreceitas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoriasAdapter.OnClickListener{
    private RecyclerView mRecyclerV;
    private FloatingActionButton mAddButton;
    private CategoriasAdapter mCategoriaAdapter;
    private List<CategoriaEntry> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerV = findViewById(R.id.mRecyclerV);
        mAddButton = findViewById(R.id.mAddButton);
        mRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        mCategoriaAdapter = new CategoriasAdapter(this);
        mRecyclerV.setAdapter(mCategoriaAdapter);
        listaCategorias = new ArrayList<>();
        listaCategorias.add(new CategoriaEntry(1, "Doces" ));
        listaCategorias.add(new CategoriaEntry(2, "Salgados" ));
        mCategoriaAdapter.setListaCategorias(listaCategorias);
    }

    @Override
    public void onClickItem(CategoriaEntry categoria) {
        /*Intent intent = new Intent(MainActivity.this, AtualizarListaEsperaActiviy.class);

        intent.putExtra(AtualizarListaEsperaActiviy.EXTRA_LISTA_ESPERA, listaEspera);

        startActivity(intent);*/
    }
}
