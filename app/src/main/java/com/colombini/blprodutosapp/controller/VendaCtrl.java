package com.colombini.blprodutosapp.controller;

import com.colombini.blprodutosapp.DAO.VendaDAO;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Venda;

import java.util.List;

public class VendaCtrl {

    private final VendaDAO vendaDAO;

    public VendaCtrl(ConexaoSQLite pConexaoSQLite){
        vendaDAO = new VendaDAO(pConexaoSQLite);
    }

    public long salvarVendaCtrl(Venda pVenda){
        return vendaDAO.salvarVendaDAO(pVenda);
    }

    public boolean salvarItensVendaCtrl(Venda pvenda) {
        return vendaDAO.salvarItensDaVendaDAO(pvenda);
    }

    public List<Venda> listarVendaCtrl() {
        return vendaDAO.listarVendasDAO();

    }

}
