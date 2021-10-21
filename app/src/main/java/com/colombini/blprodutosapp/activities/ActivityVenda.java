package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.adapters.AdapterItensDoCarrinho;
import com.colombini.blprodutosapp.controller.ProdutoCtrl;
import com.colombini.blprodutosapp.controller.VendaCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.ItemDoCarrinho;
import com.colombini.blprodutosapp.model.Produto;
import com.colombini.blprodutosapp.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityVenda extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoCtrl produtoCtrl;
    private EditText quantidadeItem;
    private TextView tvTotalVenda;

    //carrinho de compras
    private ListView lsvCarrinhoCompras;
    private List<ItemDoCarrinho>listaItensDoCarrinho;
    private AdapterItensDoCarrinho adpItemDoCarrinho;

    //controllers
    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstancia(this));

            //spinner
            this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(this));
            this.listaProduto = this.produtoCtrl.getListaProdutosCTrl();

            this.spnProdutos = (Spinner)  this.findViewById(R.id.spnProduto);
            ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<Produto>(
                    this,
                    android.R.layout.simple_spinner_item,
                    this.listaProduto);

            this.spnProdutos.setAdapter(spnProdutoAdapter);

            //end spinner

        this.quantidadeItem = (EditText) this.findViewById(R.id.edtQuantidadeProduto);

        this.tvTotalVenda = (TextView) this.findViewById(R.id.tvTotalVenda);

        //variaveis e objetos do carrinho de compras
        this.lsvCarrinhoCompras = (ListView) this.findViewById(R.id.lsvProdutos);
        this.listaItensDoCarrinho = new ArrayList<>();
        this.adpItemDoCarrinho = new AdapterItensDoCarrinho(ActivityVenda.this, this.listaItensDoCarrinho);
        this.lsvCarrinhoCompras.setAdapter(this.adpItemDoCarrinho);

        this.lsvCarrinhoCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                final ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) adpItemDoCarrinho.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ActivityVenda.this);
                janelaDeEscolha.setTitle("Escolha");
                janelaDeEscolha.setMessage("Deseja remover o item " + itemDoCarrinho.getNome() + "?");

                janelaDeEscolha.setNegativeButton("Não", null);

                janelaDeEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        boolean excluiu = false;

                        excluiu = adpItemDoCarrinho.removerItemDoCarrinho(posicao);

                        double totalVenda = calcularTotalVenda(listaItensDoCarrinho);
                        atualizarValorTotalVenda(totalVenda);

                        if (!excluiu){
                            Toast.makeText(ActivityVenda.this, "Erro ao excluir item do carrinho", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaDeEscolha.create().show();

            }
        });

    }

    /**
     * Clique no botão de finalizar venda
     * @param view
     */

    public void eventFecharVenda(View view){
        Venda vendaFechada = this.criarVenda();
        this.salvarVenda(vendaFechada);

        if (this.salvarVenda(vendaFechada)  == true){
            Toast.makeText(this, "Venda Fechada com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao fechar venda", Toast.LENGTH_SHORT).show();
        }
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
        itemDoCarrinho.setIdProduto(produtoSelecionado.getId());
        itemDoCarrinho.setQuantidadeSelecionada(quantidadeProduto);
        itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
        itemDoCarrinho.setPrecoDoItemVenda(itemDoCarrinho.getPrecoProduto() * itemDoCarrinho.getQuantidadeSelecionada());

        this.adpItemDoCarrinho.addItemDoCarrinho(itemDoCarrinho);

        double totalVenda = this.calcularTotalVenda(this.listaItensDoCarrinho);
        this.atualizarValorTotalVenda(totalVenda);

    }

    private double calcularTotalVenda(List<ItemDoCarrinho> pListaItensDoCarrinho){

        double totalVenda = 0.0d;

        for (ItemDoCarrinho itemDoCarrinho: pListaItensDoCarrinho) {
            totalVenda += itemDoCarrinho.getPrecoDoItemVenda();
        }

        return totalVenda;
    }

    private void atualizarValorTotalVenda(double pValorTotal){
        this.tvTotalVenda.setText(String.valueOf(pValorTotal));
    }

    private Venda criarVenda(){
        Venda venda = new Venda();
        venda.setDataDaVenda(new Date());
        venda.setItensDaVenda(this.listaItensDoCarrinho);

        return venda;
    }

    private boolean salvarVenda(Venda pVenda){

        long idvenda = vendaCtrl.salvarVendaCtrl(pVenda);

        if (idvenda > 0) {

            pVenda.setId(idvenda);

            if (vendaCtrl.salvarVendaCtrl(pVenda) > 0) {
                Toast.makeText(this, "Venda Realizada", Toast.LENGTH_SHORT).show();

            }else {Toast.makeText(this, "Venda não foi Realizada, tente novamente", Toast.LENGTH_SHORT).show();

            }

            return true;
        }

        return false;
    }

}