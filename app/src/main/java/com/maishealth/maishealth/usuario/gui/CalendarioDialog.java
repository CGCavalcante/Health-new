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
import com.maishealth.maishealth.usuario.negocio.ValidaCadastro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarioDialog extends AppCompatActivity {
    private final String[] listaHorarioMedico = {"Manhã", "Tarde", "Noite"};
    Button btnConfirmarConsultas;
    Button btnClick;
    TextView textData;
    private Spinner spinnerHorarioMedico;
    private String data;
    private int dayOfWeek;

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
                // verifica se o tamanho do dia e do mês é 1, se for acrescenta um zero na frente.
                // para uniformizar o tamanho da data.
                String dMonth = Long.toString(dayOfMonth);
                String mMonth = Long.toString(month + 1);
                if (dMonth.length()==1){
                    dMonth = "0" + dMonth;
                }
                if (mMonth.length() == 1){
                    mMonth = "0" + mMonth;
                }
                // recupera a data para verificar se é uma data válida.
                data =  dMonth + "/" + mMonth +"/"+ year;
                textData.setText(data);
                // recupera o dia da semana para validar se é um dia comercial.
                GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth-1);
                dayOfWeek =date.get(date.DAY_OF_WEEK);
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
        ValidaCadastro validaCadastro = new ValidaCadastro();
        boolean valido = true;
        if (!validaCadastro.isDataNoPassado(data)){
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (dayOfWeek == 6 || dayOfWeek == 7){
            textData.requestFocus();
            textData.setError("Data inválida!");
            valido = false;
        }
        if (valido){
            this.mudarTela(MapsActivity.class);
        }
    }
}
