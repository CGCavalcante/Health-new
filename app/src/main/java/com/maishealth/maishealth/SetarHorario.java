package com.maishealth.maishealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.gui.MenuMedicoActivity;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

import java.util.ArrayList;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_MEDICO_PREFERENCES;

public class SetarHorario extends AppCompatActivity {
    private TextView diaSemana;
    private CheckBox checkBoxM, checkBoxT, checkBoxN;
    private EditText vagasM, vagasT, vagasN;
    private Button btnConfirmar, btnVoltar;
    private String diaSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setar_horario);

        diaSemana = (TextView) findViewById(R.id.textDia);

        Intent intent = getIntent();
        String dia = intent.getStringExtra("dia");
        diaSet = dia;

        diaSemana.setText(dia);

        checkBoxM = findViewById(R.id.chkM);
        checkBoxT = findViewById(R.id.chkT);
        checkBoxN = findViewById(R.id.chkN);

        vagasM = findViewById(R.id.vagasM);
        vagasT = findViewById(R.id.vagasT);
        vagasN = findViewById(R.id.vagasN);


    }

    public void confirmarHorario(View view) throws Exception {
        ValidaCadastro validaCadastro = new ValidaCadastro();

        String vagasMS = vagasM.getText().toString();
        long vagasML = Long.parseLong(vagasMS);

        String vagasTS = vagasT.getText().toString();
        long vagasTL = Long.parseLong(vagasTS);

        String vagasNS = vagasN.getText().toString();
        long vagasNL = Long.parseLong(vagasNS);

        if (checkBoxM.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasMS)) {
                vagasM.requestFocus();
                vagasM.setError("Campo não preechido");
            } else {
                String manha = "Manhã";
                this.cadastrarHorario(diaSet, manha, vagasML);
            }
        } else {
            vagasM.requestFocus();
            vagasM.setError("Horario não será gravado");
        }

        if (checkBoxT.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasTS)) {
                vagasT.requestFocus();
                vagasT.setError("Campo não preechido");
            } else {
                String tarde = "Tarde";
                //this.cadastrarHorario(diaSet, tarde, vagasTL);
            }

        } else {
            vagasT.requestFocus();
            vagasT.setError("Horario não será gravado");
        }

        if (checkBoxN.isChecked()) {
            if (validaCadastro.isCampoVazio(vagasNS)) {
                vagasN.requestFocus();
                vagasN.setError("Campo não preechido");
            } else {
                String noite = "Noite";
                //this.cadastrarHorario(diaSet, noite, vagasNL);
            }
        } else {
            vagasN.requestFocus();
            vagasN.setError("Horario não será gravado");
        }
    }

    private void cadastrarHorario(String dia, String turno, long vagas) throws Exception {
        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
        servicosMedico.criarHorario(dia, turno, vagas);
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
