package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maishealth.maishealth.R;

public class ConfirmarConsulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_consulta);

        Toolbar toolbar = findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    
    public void mudarTela(Class tela){
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        this.mudarTela(MenuPaciente.class);
    }
    
    public void voltarParaMenu(View view){
        this.mudarTela(MenuPaciente.class);
    }
}
