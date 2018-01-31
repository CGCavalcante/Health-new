package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

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

        btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                        try {
                            servicosMedico.criarHorario(diaSet, manha, vagasML);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
                        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                        try {
                            servicosMedico.criarHorario(diaSet, tarde, vagasTL);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
                        ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
                        try {
                            servicosMedico.criarHorario(diaSet, noite, vagasNL);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    vagasN.requestFocus();
                    vagasN.setError("Horario não será gravado");
                }

            }
        });

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
