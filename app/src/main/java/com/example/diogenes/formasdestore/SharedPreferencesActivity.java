package com.example.diogenes.formasdestore;

import android.app.Presentation;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.diogenes.formasdestore.util.Preferences;

import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextIdade;
    private Switch switchVivo;
    private boolean vivo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        editTextNome = findViewById(R.id.edtNome);
        editTextIdade = findViewById(R.id.edtIdade);
        switchVivo = findViewById(R.id.swVivo);

        if (Preferences.getAllKeys(getBaseContext()).size() > 0) {
            Log.v("store", "EXISTE PREFERÊNCIAS");
        }


//        Map<String, ?> listKeys = Preferences.getAllKeys(getBaseContext());
        Log.v("store", "CREATING");
        if (Preferences.isKeyExists(getBaseContext(), "nome")) {
            Log.v("store", "Exibindo nome: " + editTextNome.getText().toString());
            editTextNome.setText(Preferences.getString(getBaseContext(), "nome"));
        }
        if (Preferences.isKeyExists(getBaseContext(), "idade")) {
            Log.v("store", "Exibindo idade: " + editTextIdade.getText().toString());
            editTextIdade.setText(Preferences.getInterger(getBaseContext(), "idade"));
        }
        if (Preferences.isKeyExists(getBaseContext(), "vivo")) {
            Log.v("store", "Exibindo estado:  " + Boolean.toString(switchVivo.isActivated()));
            switchVivo.setChecked(Preferences.getBoolean(getBaseContext(), "vivo"));
        }
    }

    public void salvarPreferences(View view) {
        Preferences.setString(getBaseContext(), "nome", editTextNome.getText().toString());
        Preferences.setInterger(getBaseContext(), "idade", Integer.parseInt(editTextIdade.getText().toString()));
        Preferences.setBoolean(getBaseContext(), "vivo", switchVivo.isChecked());
        Log.v("store", editTextNome.getText().toString());
        Log.v("store", editTextIdade.getText().toString());
        Log.v("store", Boolean.toString(switchVivo.isChecked()));
        Log.v("store", "Preferências salvas");
        finish();

    }



    public void ativar(View view) {
        Log.v("store", "Vivo: " +vivo);
        if (vivo){
            switchVivo.setChecked(true);
        }else{
            switchVivo.setChecked(false);
        }
    }

    public void apagarPreferences(View view) {
        Log.v("store", "Preferências apagadas");
        editTextNome.setText("");
        editTextIdade.setText("");
        switchVivo.setChecked(false);
        Preferences.clear(getBaseContext());
        finish();
    }
}
