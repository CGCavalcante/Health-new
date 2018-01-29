package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.gui.MenuMedicoActivity;

public class SetarHorario extends AppCompatActivity {
    private TextView diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setar_horario);

        diaSemana = (TextView) findViewById(R.id.textDia);

        Intent intent = getIntent();
        String dia = intent.getStringExtra("dia");

        diaSemana.setText(dia);
    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltarHoraMed(View view) {
        this.mudarTela(HorarioMedicoAct.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(HorarioMedicoAct.class);
    }
}
