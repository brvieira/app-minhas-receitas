package com.bruno.minhasreceitas.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bruno.minhasreceitas.R;
import com.bruno.minhasreceitas.model.Categoria;
import com.bruno.minhasreceitas.view.adapter.CategoriasAdapter;

import java.util.ArrayList;
import java.util.List;

public class NovaReceitaCategoria extends AppCompatActivity implements CategoriasAdapter.OnClickListener{
    private RecyclerView mRecyclerV;
    private CategoriasAdapter mCategoriaAdapter;
    private List<Categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita_categoria);
        mRecyclerV = findViewById(R.id.mRecyclerV);
        mRecyclerV.setLayoutManager(new LinearLayoutManager(this));
        mCategoriaAdapter = new CategoriasAdapter(this);
        mRecyclerV.setAdapter(mCategoriaAdapter);
        listaCategorias = new ArrayList<>();
        listaCategorias.add(new Categoria(1, "Doces" ));
        listaCategorias.add(new Categoria(2, "Salgados" ));
        mCategoriaAdapter.setListaCategorias(listaCategorias);
    }

    @Override
    public void onClickItem(Categoria categoria) {
        Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();
    }
}
