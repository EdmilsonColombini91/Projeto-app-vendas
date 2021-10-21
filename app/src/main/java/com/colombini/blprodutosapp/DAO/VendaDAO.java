package com.colombini.blprodutosapp.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.colombini.blprodutosapp.dbHelper.ConexaoSQLite;
import com.colombini.blprodutosapp.model.ItemDoCarrinho;
import com.colombini.blprodutosapp.model.Venda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDAO {

    private final ConexaoSQLite conexaoSQLite;

    public VendaDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarVendaDAO(Venda pvenda) {

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();
            values.put("data", pvenda.getDataDaVenda().getTime());

            long idVendaInserido = db.insert("venda", null, values);

            return  idVendaInserido;

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (db != null) {
                db.close();
            }
        }

        return 0;

    }

    public boolean salvarItensDaVendaDAO(Venda pvenda) {

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {

            ContentValues values = null;

            for (ItemDoCarrinho itemDaVenda : pvenda.getItemDaVenda()) {

                values = new ContentValues();
                values.put("quantidade_vendida", itemDaVenda.getQuantidadeSelecionada());
                values.put("id_produto", itemDaVenda.getIdProduto());
                values.put("id_venda", pvenda.getId());
                values.put("preco_item", itemDaVenda.getPrecoDoItemVenda());

                db.insert("item_da_venda", null, values);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return false;
    }

    public List<Venda> listarVendasDAO() {

        List<Venda> listaVendas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query =
                "SELECT" +
                        " venda.id," +
                        " venda.data," +
                        " SUM(produto.preco)," +
                        " COUNT(produto.id)" +
                        " FROM venda" +
                        " INNER JOIN item_da_venda ON (venda.id = item_da_venda.id_venda)" +
                        " INNER JOIN produto ON (item_da_venda.id_produto = produto.id)" +
                        " GROUP BY venda.id;";

        try {

            db = this.conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                Venda vendaTemp = null;

                do {

                    vendaTemp = new Venda();
                    vendaTemp.setId(cursor.getLong(0));
                    vendaTemp.setDataDaVenda(new Date(cursor.getLong(1)));
                    vendaTemp.setTotalVenda(cursor.getDouble(2));
                    vendaTemp.setQteItens(cursor.getInt(3));

                    listaVendas.add(vendaTemp);

                } while (cursor.moveToNext());
            }

        }catch (Exception e) {
            Log.d("ERRO VENDAS", "ERRO ao retornar as vendas");
            return null;
        }

        return listaVendas;
    }
}
