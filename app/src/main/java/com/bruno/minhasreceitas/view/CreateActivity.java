package com.bruno.minhasreceitas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bruno.minhasreceitas.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }

    public void onClickCategoria(View v) {
        Intent intent = new Intent(CreateActivity.this, NovaCategoria.class);
        startActivity(intent);
    }


    public void onClickReceita(View v) {
        Intent intent = new Intent(CreateActivity.this, NovaReceitaCategoria.class);
        startActivity(intent);
    }
}
