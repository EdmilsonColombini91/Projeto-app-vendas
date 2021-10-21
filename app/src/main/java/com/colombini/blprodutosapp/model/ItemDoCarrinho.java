package com.colombini.blprodutosapp.model;

public class ItemDoCarrinho {

    private long id;
    private String nome;
    private int quantidadeSelecionada;
    private long idProduto;
    private double precoProduto;
    private double precoDoItemVenda; //preco do item vendido = quantidade * preco

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeSelecionada() {
        return quantidadeSelecionada;
    }

    public void setQuantidadeSelecionada(int quantidadeSelecionada) {
        this.quantidadeSelecionada = quantidadeSelecionada;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPrecoDoItemVenda() {
        return precoDoItemVenda;
    }

    public void setPrecoDoItemVenda(double precoDoItemVenda) {
        this.precoDoItemVenda = precoDoItemVenda;
    }
}
