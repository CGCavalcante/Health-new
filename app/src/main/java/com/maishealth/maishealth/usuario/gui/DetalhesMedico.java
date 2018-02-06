package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.dominio.DadosMedico;

public class DetalhesMedico extends AppCompatActivity {
    ImageView fotoMedico;
    TextView nomeMedico;
    TextView especMedico;
    TextView dataCons;
    TextView turnoCons;
    private  String data;
    private  String turno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        nomeMedico = findViewById(R.id.nomeMedico);
        especMedico =  findViewById(R.id.especMedico);
        dataCons =  findViewById(R.id.dataCons);
        turnoCons = findViewById(R.id.turnoCons);

        Intent intent = new Intent();
        data =  intent.getStringExtra("data1");
        turno =  intent.getStringExtra("turno1");
        //String idmedicoS = intent.getStringExtra ("idmedico" );
        //long idMedico = Integer.parseInt(idmedicoS);
        dataCons.setText(data);
        turnoCons.setText(turno);


        /*DadosMedico obj = (DadosMedico) getIntent().getExtras().getSerializable("objeto");
        nomeMedico.setText(obj.getNome());
        especMedico.setText(obj.getEspecialidade());
        fotoMedico.setImageResource(obj.getImagem());*/
    }
}
