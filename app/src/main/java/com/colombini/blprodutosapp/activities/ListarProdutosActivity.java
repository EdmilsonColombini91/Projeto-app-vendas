package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.adapters.AdapterListaProdutos;
import com.colombini.blprodutosapp.controller.ProdutoCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private ProdutoCtrl produtoCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ListarProdutosActivity.this));

        produtoList = produtoCtrl.getListaProdutosCTrl();

        this.lsvProdutos = (ListView) findViewById(R.id.lsvProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ListarProdutosActivity.this, this.produtoList);

        this.lsvProdutos.setAdapter(this.adapterListaProdutos);

        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Produto produtoSelecionado = (Produto) adapterListaProdutos.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ListarProdutosActivity.this);

                janelaDeEscolha.setTitle("Escolha");
                janelaDeEscolha.setMessage("O que deseja fazer com o produto selecionado?");

                janelaDeEscolha.setNeutralButton("Cancelar", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        boolean excluiu = produtoCtrl.excluirProdutoCtrl(produtoSelecionado.getId());

                        dialogInterface.cancel();

                        if (excluiu == true){

                            adapterListaProdutos.removerProduto(posicao);

                            Toast.makeText(ListarProdutosActivity.this, "Produto excluido com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ListarProdutosActivity.this, "Erro ao excluir produto", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                janelaDeEscolha.setPositiveButton("Editar", (dialogInterface, i) -> {

                    Bundle bundleDadosProduto = new Bundle();

                    bundleDadosProduto.putLong("id_produto", produtoSelecionado.getId());
                    bundleDadosProduto.putString("nome_produto", produtoSelecionado.getNome());
                    bundleDadosProduto.putDouble("preco_produto", produtoSelecionado.getPreco());
                    bundleDadosProduto.putInt("estoque_produto", produtoSelecionado.getQuantidadeEmEstoque());

                    Intent intentEditarProdutos = new Intent(ListarProdutosActivity.this, EditarProdutosActivity.class);
                    intentEditarProdutos.putExtras(bundleDadosProduto);
                    startActivity(intentEditarProdutos);

                });

                janelaDeEscolha.create().show();
            }
        });

    }

    //executa o evento de click no botao atualizar
    public void eventAtualizarProdutos(View view){
        this.adapterListaProdutos.atualizar(this.produtoCtrl.getListaProdutosCTrl());
    }
}