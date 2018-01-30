package com.maishealth.maishealth.usuario.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maishealth.maishealth.R;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        lista = (ListView) findViewById(R.id.lista);

        ArrayList<String> listadados = preencher();

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listadados);
        lista.setAdapter(a);

    }

    private ArrayList<String> preencher() {
        ArrayList<String> dados = new ArrayList<String>();

        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");
        dados.add("ffff");

        return dados;
    }
}
