package com.example.diogenes.formasdestore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diogenes.formasdestore.dao.PessoaDB;
import com.example.diogenes.formasdestore.model.Pessoa;
import com.example.diogenes.formasdestore.util.PessoasAdapter;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private PessoaDB pessoaDB;
    private ArrayList<Pessoa> listaPessoas;
    private ListView listView;
    private EditText editTextNome;
    private EditText editTextIdade;
    private Switch switchVivo;
    private Button btEditar;
    private Button btAdicionar;

    PessoasAdapter pessoasAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        pessoaDB = new PessoaDB(getBaseContext());

        editTextNome = findViewById(R.id.edtNomeSQLite);
        editTextIdade = findViewById(R.id.edtIdadeSQLite);
        switchVivo = findViewById(R.id.swVivoSQLite);
        btEditar = findViewById(R.id.btAlterar);
        btAdicionar = findViewById(R.id.btAdicionar);

        listaPessoas = pessoaDB.findAll();

        pessoasAdapter = new PessoasAdapter(this, listaPessoas);
        listView = (ListView) findViewById(R.id.lvPessoas);
        listView.setAdapter(pessoasAdapter);
        listView.setOnItemClickListener(this);
    }

    public void adicionarPessoa(View view) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(editTextNome.getText().toString());
        pessoa.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
        pessoa.setVivo(switchVivo.isChecked());
        pessoaDB.inserir(pessoa);

        listaPessoas.add(pessoa);
        pessoasAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), pessoa.getNome() +" adicionada com sucesso", Toast.LENGTH_SHORT);

        //LIMPAR CAMPOS
        editTextNome.setText(null);
        editTextIdade.setText(null);
        switchVivo.setChecked(false);
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
        //SETAR DADOS NO EDIT
        final Pessoa pessoa = (Pessoa) parent.getAdapter().getItem(position);

        editTextIdade.setText(String.valueOf(pessoa.getIdade()));
        editTextNome.setText(pessoa.getNome());
        switchVivo.setChecked(pessoa.isVivo());

        btEditar.setEnabled(true);
        btAdicionar.setEnabled(false);

        ImageView deleteIcon = (ImageView) view.findViewById(R.id.ibTrash);
        if (deleteIcon != null) {
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pessoaDB.delete(pessoa.getCodigo());
                    listaPessoas.remove(position);
                    pessoasAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Pessoa deletada com sucesso", Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void alterar(View view) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(editTextNome.getText().toString());
        pessoa.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
        pessoa.setVivo(switchVivo.isChecked());
        pessoaDB.alterar(pessoa);

        listaPessoas.remove(pessoa.getCodigo()-1);
        listaPessoas.add(pessoa);
        pessoasAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), pessoa.getNome() +" alterada com sucesso", Toast.LENGTH_SHORT);

        //LIMPAR CAMPOS
        editTextNome.setText(null);
        editTextIdade.setText(null);
        switchVivo.setChecked(false);

        btEditar.setEnabled(false);
        btAdicionar.setEnabled(true);
    }
}
