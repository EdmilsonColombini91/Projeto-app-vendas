package com.colombini.blprodutosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.colombini.blprodutosapp.activities.ActivityProduto;
import com.colombini.blprodutosapp.activities.ListarProdutosActivity;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroProdutos;
    private Button btnListarProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite.getInstancia(this);

        this.btnCadastroProdutos = (Button) findViewById(R.id.btnCadastroProdutos);

        System.out.println(R.id.btnListarProdutos);
        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        this.btnCadastroProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //executado ao clicar no botão
                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);
            }
        });

        this.btnListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                startActivity(intent);
            }
        });
    }
}