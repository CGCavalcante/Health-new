package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Adaptador;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPosto;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaMedicos extends AppCompatActivity {
    private String especialidade;
    private String data;
    private String turno;
    ListView listaMedicos;
    ArrayList<DadosMedico> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        listaMedicos = (ListView) findViewById(R.id.lstMedicos);
        Intent intent = getIntent();
        especialidade = intent.getStringExtra("espec");
        data = intent.getStringExtra("data");
        turno = intent.getStringExtra("turno");

        lista = preencher();



        Adaptador adaptador = new Adaptador(getApplication(), lista);

        listaMedicos.setAdapter(adaptador);

        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DadosMedico obj = (DadosMedico) parent.getItemAtPosition(position);

                GuiUtil.myToast(getApplicationContext(), "Especialidade:" + obj.getEspecialidade());

                Intent passar = new Intent(getApplicationContext(), DetalhesMedico.class);
                //passar.putExtra("objeto", (Serializable) obj);
                String idMedico = Long.toString( obj.getIdmedico());
                passar.putExtra("idmedico", idMedico);
                passar.putExtra("data1", data);
                passar.putExtra("turno1", turno );
                startActivity(passar);

            }
        });

    }

    private ArrayList<DadosMedico> preencher() {
        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
        ServicosPosto servicosPosto = new ServicosPosto(getApplicationContext());

        //ArrayList<DadosMedico> dados = servicosPosto.returnNomeMedicos(1);
        ArrayList<Medico> medicos = servicosMedico.getMedicoByEspec(especialidade);
        ArrayList<DadosMedico> dados2 = servicosPosto.medicosEspec(especialidade);

        return dados2;
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltar(View view) {
        this.mudarTela(LoginActivity.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(LoginActivity.class);
    }
}
