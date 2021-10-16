package com.colombini.blprodutosapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.colombini.blprodutosapp.activities.ActivityProduto;
import com.colombini.blprodutosapp.activities.ActivityVenda;
import com.colombini.blprodutosapp.activities.ListarProdutosActivity;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroProdutos;
    private Button btnListarProdutos;
    private Button btnVender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite.getInstancia(this);

        this.btnCadastroProdutos = (Button) findViewById(R.id.btnCadastroProdutos);

        this.btnListarProdutos = (Button) findViewById(R.id.btnListarProdutos);

        this.btnVender = (Button) findViewById(R.id.btnVender);

        this.btnCadastroProdutos.setOnClickListener((view) -> {

                //executado ao clicar no botão
                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);
        });

        this.btnListarProdutos.setOnClickListener((viw) -> {
                Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                startActivity(intent);
        });

        this.btnVender.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ActivityVenda.class);
                startActivity(intent);
            }
        });
    }
}