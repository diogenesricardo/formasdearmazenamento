package com.example.diogenes.formasdestore.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diogenes.formasdestore.R;
import com.example.diogenes.formasdestore.dao.PessoaDB;
import com.example.diogenes.formasdestore.model.Pessoa;

import java.util.ArrayList;

public class PessoasAdapter extends BaseAdapter {

    private ArrayList<Pessoa> listaPessoas;
    private Context context;
    private PessoaDB pessoaDB;

    public PessoasAdapter(Context context, ArrayList<Pessoa> listaPessoas) {
        super();
        this.context = context;
        pessoaDB = new PessoaDB(context);
        this.listaPessoas = listaPessoas;

    }

    @Override
    public int getCount() {
        return listaPessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pessoa pessoa = listaPessoas.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pessoa
                , parent, false);

        TextView t = (TextView) view.findViewById(R.id.twNomeAdapter);
        t.setText(pessoa.getNome());

        ImageView imageViewVivo = (ImageView) view.findViewById(R.id.ibSkull);
        if(!pessoa.isVivo()){
            imageViewVivo.setImageResource(R.drawable.skull);
        }

        ImageView imageViewTrash = (ImageView) view.findViewById(R.id.ibTrash);
        imageViewTrash.setImageResource(R.drawable.garbage);

        return view;
    }
}
