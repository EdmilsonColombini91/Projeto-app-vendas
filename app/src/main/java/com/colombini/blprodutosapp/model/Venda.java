package com.colombini.blprodutosapp.model;

import java.util.Date;
import java.util.List;

public class Venda {

    private long id;
    private Date dataDaVenda;
    private String nomeDoCliente;
    private List<ItemDoCarrinho> itemDaVenda;
    private double totalVenda;
    private int qteItens;


    public Venda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(Date dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public List<ItemDoCarrinho> getItemDaVenda() {
        return itemDaVenda;
    }

    public void setItensDaVenda(List<ItemDoCarrinho> itemDaVenda) {
        this.itemDaVenda = itemDaVenda;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public int getQteItens() {
        return qteItens;
    }

    public void setQteItens(int qteItens) {
        this.qteItens = qteItens;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataDaVenda=" + dataDaVenda +
                ", nomeDoCliente='" + nomeDoCliente + '\'' +
                '}';
    }
}
