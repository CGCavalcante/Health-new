package com.maishealth.maishealth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HorarioMedicoAct extends AppCompatActivity {
    private Button btnSeg;
    private Button btnQua;
    private Button btnQui;
    private Button btnSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_medico);

        btnSeg = (Button) findViewById(R.id.seg);
        btnQua = (Button) findViewById(R.id.qua);
        btnQui = (Button) findViewById(R.id.qui);
        btnSex = (Button) findViewById(R.id.sex);
    }


    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void horTerca(View view) {
        mudarTela(SetarHorario.class);
    }
}
