package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.controller.ProdutoCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Produto;

public class ActivityProduto extends AppCompatActivity {

    private EditText edtIdProduto; //Codigo de barras do produto
    private EditText edtNomeProduto;
    private EditText edtQuantidadeProduto;
    private EditText edtPrecoProduto;

    private Button btnSalvarProduto;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtQuantidadeProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);

        btnSalvarProduto = (Button) findViewById(R.id.btnSalvarProduto);

        this.clickNoBotaoSalvarListener();

    }

    private void clickNoBotaoSalvarListener(){
        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Produto produtoACadastrar = getDadosProdutoDoFormulario();

                if (produtoACadastrar != null) {

                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ActivityProduto.this));
                    long idProduto = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);

                    if (idProduto > 0){
                        Toast.makeText(ActivityProduto.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityProduto.this, "Produto não pode ser salvo", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private Produto getDadosProdutoDoFormulario() {

        this.produto = new Produto();

        if (this.edtIdProduto.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
        }else{
            return null;
        }
        if (this.edtNomeProduto.getText().toString().isEmpty() == false) {
            this.produto.setNome(this.edtNomeProduto.getText().toString());
        }else{
            return null;
        }
        if (edtQuantidadeProduto.getText().toString().isEmpty() == false) {
            int QuantidadeProduto = Integer.parseInt(this.edtQuantidadeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(QuantidadeProduto);
        }else{
            return null;
        }

        if (edtPrecoProduto.getText().toString().isEmpty() == false) {
            double precoProduto = Double.parseDouble(this.edtPrecoProduto.getText().toString());
            this.produto.setPreco(precoProduto);
        }else{
            return null;
        }

        return produto;
    }

}