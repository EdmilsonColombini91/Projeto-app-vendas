package com.colombini.blprodutosapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.model.ItemDoCarrinho;
import com.colombini.blprodutosapp.model.Produto;

import java.util.List;

/**
 * Created by Edmilson 17/10/2021
 */

public class AdapterItensDoCarrinho extends BaseAdapter {

    private Context context;
    private List<ItemDoCarrinho> itensDoCarrinho;

    public AdapterItensDoCarrinho(Context context, List<ItemDoCarrinho> itensDoCarrinho) {
        this.context = context;
        this.itensDoCarrinho = itensDoCarrinho;
    }

    @Override
    public int getCount() {return this.itensDoCarrinho.size();}

    @Override
    public Object getItem(int posicao) {return this.itensDoCarrinho.get(posicao);}

    @Override
    public long getItemId(int posicao) {return posicao;}

    public boolean removerItemDoCarrinho (int posicao) {
        this.itensDoCarrinho.remove(posicao);
        notifyDataSetChanged();

        return true;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.layout_carrinho_produtos, null);

        TextView tvNomeProduto = (TextView)  v.findViewById(R.id.tvNomeProduto);
        TextView tvPrecoProduto = (TextView)  v.findViewById(R.id.tvPrecoProduto);
        TextView tvQuantidadeSelecionada = (TextView)  v.findViewById(R.id.tvQteProduto);
        TextView tvValorItem = (TextView) v.findViewById(R.id.tvValorTotalItem);

        tvNomeProduto.setText(this.itensDoCarrinho.get(posicao).getNome());
        tvPrecoProduto.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getPrecoProduto()));
        tvQuantidadeSelecionada.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getQuantidadeSelecionada()));
        tvValorItem.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getPrecoDoItemVenda()));

        return v;
    }

    /**
     * Adiciona item ao carrinho
     * @param pItemDoCarrinho
     */

    public void addItemDoCarrinho(ItemDoCarrinho pItemDoCarrinho){
        this.itensDoCarrinho.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }

    /**
     * Atualizar a lista de produtos do adapter
     * @param pItensDoCarrinho
     */

    @SuppressWarnings("JavadocReference")
    public void atualizar(List<ItemDoCarrinho> pItensDoCarrinho){
        this.itensDoCarrinho.clear();
        this.itensDoCarrinho = pItensDoCarrinho;
        this.notifyDataSetChanged();
    }

}
