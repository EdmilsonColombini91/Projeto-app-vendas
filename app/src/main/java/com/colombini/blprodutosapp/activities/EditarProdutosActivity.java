package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.controller.ProdutoCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Produto;

public class EditarProdutosActivity extends AppCompatActivity {

    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtPrecoProduto;
    private EditText edtEstoqueProduto;

    private Button btnSalvarAlteracoes;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produtos);

        this.edtIdProduto = (EditText) findViewById(R.id.edtIdProduto);
        this.edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        this.edtPrecoProduto = (EditText) findViewById(R.id.edtPrecoProduto);
        this.edtEstoqueProduto = (EditText) findViewById(R.id.edtQuantidadeProduto);

        this.btnSalvarAlteracoes = (Button) findViewById(R.id.btnSalvarProduto);

        Bundle bundleDadosProduto = getIntent().getExtras();

        Produto produto = new Produto();

        produto.setId(bundleDadosProduto.getLong("id_produto"));
        produto.setNome(bundleDadosProduto.getString("nome_produto"));
        produto.setPreco(bundleDadosProduto.getDouble("preco_produto"));
        produto.setQuantidadeEmEstoque(bundleDadosProduto.getInt("estoque_produto"));

        this.setDadosProduto(produto);

        this.clickNoBotaoSalvarListener();

    }

    private void setDadosProduto(Produto produto){

        this.edtIdProduto.setText(String.valueOf(produto.getId()));
        this.edtNomeProduto.setText(produto.getNome());
        this.edtPrecoProduto.setText(String.valueOf(produto.getPreco()));
        this.edtEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEmEstoque()));

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
        if (this.edtEstoqueProduto.getText().toString().isEmpty() == false) {
            int QuantidadeProduto = Integer.parseInt(this.edtEstoqueProduto.getText().toString());
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

    private void clickNoBotaoSalvarListener(){
        this.btnSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Produto produtoACadastrar = getDadosProdutoDoFormulario();

                Log.d("PRODUTO RECUPERADO", produtoACadastrar.toString());

                if (produtoACadastrar != null) {
//
//                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ActivityProduto.this));
//                    long idProduto = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);
//
//                    if (idProduto > 0){
//                        Toast.makeText(ActivityProduto.this, "Produto salvo com sucesso!", Toast.LENGTH_LONG).show();
//                    }else{
//                        Toast.makeText(ActivityProduto.this, "Produto não pode ser salvo", Toast.LENGTH_LONG).show();
//                    }
//
//                } else {
//                    Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}