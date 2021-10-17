package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.controller.ProdutoCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.ItemDoCarrinho;
import com.colombini.blprodutosapp.model.Produto;

import java.util.List;

public class ActivityVenda extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoCtrl produtoCtrl;
    private EditText quantidadeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        //spinner
        this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(this));
        this.listaProduto = this.produtoCtrl.getListaProdutosCTrl();

        this.spnProdutos = (Spinner)  this.findViewById(R.id.spnProduto);
        ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                this.listaProduto
        );

        this.spnProdutos.setAdapter(spnProdutoAdapter);

        //end spinner

        this
                .quantidadeItem = (EditText) this.findViewById(R.id.edtQuantidadeProduto);

    }

    /**
     * Clique no botão de add produtos ao carrinho
     * @param view
     */
    public void eventAddProduto(View view){

        ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho();
        Produto produtoSelecionado = (Produto) this.spnProdutos.getSelectedItem();

        int quantidadeProduto = 0;

        if (this.quantidadeItem.getText().toString().equals("")) {
            quantidadeProduto = 1;
        } else {
            quantidadeProduto = Integer.parseInt(this.quantidadeItem.getText().toString());
        }

        itemDoCarrinho.setNome(produtoSelecionado.getNome());
        itemDoCarrinho.setQuantidadeSelecionada(quantidadeProduto);
        itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
        itemDoCarrinho.setPrecoDoItemVenda(itemDoCarrinho.getPrecoProduto() * itemDoCarrinho.getQuantidadeSelecionada());



    }
}