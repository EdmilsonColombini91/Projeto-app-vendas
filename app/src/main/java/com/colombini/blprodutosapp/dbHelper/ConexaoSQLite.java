package com.colombini.blprodutosapp.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Create by Edmilson 22/09/2021
 */

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "bl_produtos_app";

    public ConexaoSQLite(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstancia(Context context) {
        if (INSTANCIA_CONEXAO == null) {
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT," +
                        "quantidade_em_estoque INTEGER," +
                        "preco REAL" +
                        ")";


        sqLiteDatabase.execSQL(sqlTabelaProduto);

        String sqlTabelaVenda =
                "CREATE TABLE IF NOT EXISTS venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "data INTEGER" +
                        ")";

        sqLiteDatabase.execSQL(sqlTabelaVenda);

        String sqlTableItemDaVenda =
                "CREATE TABLE IF NOT EXISTS item_da_venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "quantidade_vendida INTEGER," +
                        "id_produto INTEGER," +
                        "id_venda INTEGER" +
                        ")";
        sqLiteDatabase.execSQL(sqlTableItemDaVenda);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
