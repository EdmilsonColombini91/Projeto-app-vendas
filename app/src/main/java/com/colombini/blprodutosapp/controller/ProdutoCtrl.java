package com.colombini.blprodutosapp.controller;

import com.colombini.blprodutosapp.DAO.ProdutoDAO;
import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.Produto;

import java.util.List;

public class ProdutoCtrl {

    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl(ConexaoSQLite pConexaoSQLite){
        produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }

    public long salvarProdutoCtrl(Produto pProduto){
        return this.produtoDAO.salvarProdutoDAO(pProduto);
    }

    public List<Produto> getListaProdutosCTrl() {
        return this.produtoDAO.getListaProdutosDAO();
    }

    public boolean excluirProdutoCtrl(long pIdProduto){
        return this.produtoDAO.excluirProdutoDAO(pIdProduto);
    }

    public boolean atualizarProdutoCtrl(Produto produto){
        return this.produtoDAO.atualizarProdutoDAO(produto);
    }

}
