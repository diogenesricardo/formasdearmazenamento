package com.example.diogenes.formasdestore.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.diogenes.formasdestore.model.Pessoa;

import java.util.ArrayList;

public class PessoaDB extends SQLiteOpenHelper {

    private static final String TAG = "sql";

    // Nome do banco
    private static final String NOME_BANCO = "fafica.mobile";
    private static final int VERSAO_BANCO = 1;

    public PessoaDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela pessoa...");
        db.execSQL("CREATE TABLE IF NOT EXISTS pessoas " +
                "(codigo integer PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, " +
                "idade INTERGER," +
                " vivo BOOLEAN);");
        Log.d(TAG, "Tabela Pessoa criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso mude a versão do banco de dados, podemos executar um SQL aqui
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL("ALTER TABLE pessoas ADD dataAniversario DATE");
        }
    }

    // Insere um nova pessoa
    public long inserir(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", pessoa.getNome());
            values.put("idade", pessoa.getIdade());
            values.put("vivo", pessoa.isVivo());

            // insert into carro values (...)
            long id = db.insert("pessoas", "", values);

            Log.v(TAG, "Pessoa adicionada com sucesso" + pessoa.getNome());
            return id;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todas as pessoas
    public ArrayList<Pessoa> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from carro
            Cursor c = db.query("pessoas", null, null, null, null, null, null, null);

            ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                // The Cursor is now set to the right position
                Pessoa p = new Pessoa();

                p.setCodigo(c.getInt(c.getColumnIndex("codigo")));
                p.setNome(c.getString(c.getColumnIndex("nome")));
                p.setIdade(c.getInt(c.getColumnIndex("idade")));
                p.setVivo(c.getInt(c.getColumnIndex("vivo")) > 0);

                listaPessoas.add(p);

            }
            return listaPessoas;
        } finally {
            db.close();
        }
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete("pessoas", "codigo=" + id, null);
            //db.delete("pessoas", "codigo=?", new String[]{String.valueOf(id)});
            Log.v(TAG, "Pessoa deletada com sucesso");
        } finally {
            db.close();
        }

    }


    public long alterar(Pessoa pessoa) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", pessoa.getNome());
            values.put("idade", pessoa.getIdade());
            values.put("vivo", pessoa.isVivo());

            long id = db.update("pessoas", values, "codigo = "+pessoa.getCodigo(),null);

            Log.v(TAG, "Pessoa alterada com sucesso" + pessoa.getNome());
            return id;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            db.close();
        }
    }
}
