package com.maishealth.maishealth.usuario.gui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.maishealth.maishealth.R;

import java.util.Calendar;

public class CalendarioDialog extends AppCompatActivity {
    private final String[] listaHorarioMedico = {"Manhã", "Tarde", "Noite"};
    Button btnConfirmarConsultas;
    Button btnClick;
    TextView textData;
    private Spinner spinnerHorarioMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_dialog);

        spinnerHorarioMedico = findViewById(R.id.editTextInicioHorMed);
        btnConfirmarConsultas = findViewById(R.id.bt_confirmar_hor_montado_med);

        btnClick = findViewById(R.id.btndata);
        textData = findViewById(R.id.data);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegarData();
            }
        });

        // inicia os valores do spinner:
        spinnerHorarioMedico.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaHorarioMedico));
        spinnerHorarioMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void pegarData() {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog dp = new DatePickerDialog(CalendarioDialog.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textData.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, ano, mes, dia);

        dp.show();

    }

    private void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }

    public void voltarMenuMed1(View view) {
        this.mudarTela(ListaEspecialidade.class);
    }

    @Override
    public void onBackPressed() {
        this.mudarTela(ListaEspecialidade.class);
    }

    public void marcaConsulta(View view) {

        this.mudarTela(MapsActivity.class);
    }
}
