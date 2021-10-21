package com.colombini.blprodutosapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.model.Produto;
import com.colombini.blprodutosapp.model.Venda;

import java.text.SimpleDateFormat;
import java.util.List;

/*
Created by Edmilson on 21/10/2021
 */

public class AdapterListaDasVendas extends BaseAdapter {

    private Context context;
    private List<Venda> vendaList;
    private SimpleDateFormat simpleDateFormat;

    public AdapterListaDasVendas(Context context, List<Venda> vendaList) {
        this.context = context;
        this.vendaList = vendaList;
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getCount() {return this.vendaList.size();}

    @Override
    public Object getItem(int posicao) {return this.vendaList.get(posicao);}

    @Override public long getItemId(int posicao) {return posicao;}

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.layout_minhas_vendas, null);

        TextView tvDataVenda = (TextView)  v.findViewById(R.id.tvDataVenda);
        TextView tvPrecoTotal = (TextView)  v.findViewById(R.id.tvTotalVenda);
        TextView tvQteItens = (TextView)  v.findViewById(R.id.tvQteItens);

        tvDataVenda.setText(this.simpleDateFormat.format(vendaList.get(posicao).getDataDaVenda()));
        tvPrecoTotal.setText(String.valueOf(this.vendaList.get(posicao).getTotalVenda()));
        tvQteItens.setText(String.valueOf(this.vendaList.get(posicao).getQteItens()));

        return v;
    }

    /**
     * Atualiza a lista de produtos do adapter
     * @param pVendas
     */

    public void atualizar(List<Venda> pVendas){
        this.vendaList.clear();
        this.vendaList = pVendas;
        this.notifyDataSetChanged();
    }

}
