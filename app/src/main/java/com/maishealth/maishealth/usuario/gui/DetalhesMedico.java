package com.maishealth.maishealth.usuario.gui;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        nomeMedico = findViewById(R.id.nomeMedico);
        especMedico = findViewById(R.id.especMedico);

        DadosMedico obj = (DadosMedico) getIntent().getExtras().getSerializable("objeto");

        /*nomeMedico.setText(obj.getNome());
        especMedico.setText(obj.getEspecialidade());
        fotoMedico.setImageResource(obj.getImagem());*/
    }
}
