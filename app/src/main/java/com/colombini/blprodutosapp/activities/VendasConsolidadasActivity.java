package com.colombini.blprodutosapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.colombini.blprodutosapp.R;
import com.colombini.blprodutosapp.adapters.AdapterListaDasVendas;
import com.colombini.blprodutosapp.controller.VendaCtrl;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Venda;

import java.util.List;

public class VendasConsolidadasActivity extends AppCompatActivity {

    private ListView lsvVendas;
    private List<Venda> ListaDeVendasFeitas;
    private AdapterListaDasVendas adapterListaDasVendas;
    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_consolidadas);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstancia(VendasConsolidadasActivity.this));

        ListaDeVendasFeitas = this.vendaCtrl.listarVendaCtrl();

        this.lsvVendas = (ListView)  findViewById(R.id.lsvMinhasVendas);

        this.adapterListaDasVendas = new AdapterListaDasVendas(VendasConsolidadasActivity.this, ListaDeVendasFeitas);

        this.lsvVendas.setAdapter(this.adapterListaDasVendas);

    }
}