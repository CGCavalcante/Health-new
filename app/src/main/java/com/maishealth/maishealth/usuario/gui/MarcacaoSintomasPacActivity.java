package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.infra.Mask;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

import java.util.ArrayList;
import java.util.List;

import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.DEFAULT_ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.ID_USER_PREFERENCES;
import static com.maishealth.maishealth.infra.ConstanteSharedPreferences.TITLE_PREFERENCES;

public class MarcacaoSintomasPacActivity extends AppCompatActivity {
    private static final String SINTOMA_S_INSERIDO_S_COM_SUCESSO = "Sintoma(s) Inserido(s) com sucesso!";
    private Button btnConfirmaSintoma;
    private CheckedTextView clinicoGeral;
    private CheckedTextView cardiologia;
    private CheckedTextView dermatologia;
    private CheckedTextView ginecologia;
    private CheckedTextView oftalmologia;
    private CheckedTextView ortopedia;
    private CheckedTextView pediatria;
    private CheckedTextView urologia;
    private List listaCheckbox = new ArrayList( 8);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcacao_sintomas_pac);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView lblNomePaciente = findViewById(R.id.textViewNomePacSint);
        cardiologia = findViewById(R.id.espCardiologia);
        clinicoGeral = findViewById(R.id.espClinicoGeral);
        dermatologia = findViewById(R.id.espDermatologia);
        ginecologia = findViewById(R.id.espGinecologia);
        oftalmologia = findViewById(R.id.espOftalmologia);
        ortopedia = findViewById(R.id.espOrtopedia);
        pediatria = findViewById(R.id.espPediatria);
        urologia = findViewById(R.id.espUrologia);

        ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences(TITLE_PREFERENCES, MODE_PRIVATE);
        long idUsuario = sharedPreferences.getLong(ID_USER_PREFERENCES, DEFAULT_ID_USER_PREFERENCES);

        if(idUsuario != DEFAULT_ID_USER_PREFERENCES){
            Pessoa pessoa = servicosPessoa.searchPessoaByIdUsuario(idUsuario);
            try{
                lblNomePaciente.setText(pessoa.getNome());
            }catch (Exception e){
                Log.i("MarcSint", e.getMessage());
            }
        }
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
    
    public void voltarMarcParaMenuPac(View view){
        this.mudarTela(MenuPaciente.class);
    }

    //**olhar comentário na Activity de EscolherDiaDaConsulta
    public void marcarEnviarSintomas(View view){
        EditText edtSintoma = findViewById(R.id.editTextSint);
        String edtSintomaString = edtSintoma.getText().toString();
        String listaSintomas[]= Mask.split(edtSintomaString);

        for (String sintoma: listaSintomas){
        ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
        servicosPaciente.inserirSintoma(sintoma);
        }

        for (Object checkedSintoma: listaCheckbox){
            ServicosPaciente servicosPaciente = new ServicosPaciente(getApplicationContext());
            servicosPaciente.inserirSintoma(checkedSintoma.toString());
        }

        
        GuiUtil.myToast(this, SINTOMA_S_INSERIDO_S_COM_SUCESSO);

        this.mudarTela(EscolherDiaDaConsultaActivity.class);
        
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckedTextView) view).isChecked();
        switch (view.getId()) {

            case R.id.espGinecologia:
                if (checked) {
                    ginecologia.setChecked(false);
                    listaCheckbox.remove(ginecologia.getText().toString());
                } else {
                    ginecologia.setChecked(true);
                    listaCheckbox.add(ginecologia.getText().toString());
                }

                break;

            case R.id.espClinicoGeral:
                if (checked) {
                    clinicoGeral.setChecked(false);
                    listaCheckbox.remove(clinicoGeral.getText().toString());
                    } else {
                    clinicoGeral.setChecked(true);
                    listaCheckbox.add(clinicoGeral.getText().toString());
                    }

                break;

            case R.id.espDermatologia:
                if (checked) {
                    dermatologia.setChecked(false);
                    listaCheckbox.remove(dermatologia.getText().toString());
                } else {
                    dermatologia.setChecked(true);
                    listaCheckbox.add(dermatologia.getText().toString());
                }

                break;

            case R.id.espPediatria:
                if (checked) {
                    pediatria.setChecked(false);
                    listaCheckbox.remove(pediatria.getText().toString());
                } else {
                    pediatria.setChecked(true);
                    listaCheckbox.add(pediatria.getText().toString());
                }

                break;

            case R.id.espUrologia:
                if (checked) {
                    urologia.setChecked(false);
                    listaCheckbox.remove(urologia.getText().toString());
                } else {
                    urologia.setChecked(true);
                    listaCheckbox.add(urologia.getText().toString());
                }

                break;

            case R.id.espOftalmologia:
                if (checked) {
                    oftalmologia.setChecked(false);
                    listaCheckbox.remove(oftalmologia.getText().toString());
                } else {
                    oftalmologia.setChecked(true);
                    listaCheckbox.add(oftalmologia.getText().toString());
                }

                break;

            case R.id.espOrtopedia:
                if (checked) {
                    ortopedia.setChecked(false);
                    listaCheckbox.remove(ortopedia.getText().toString());
                } else {
                    ortopedia.setChecked(true);
                    listaCheckbox.add(ortopedia.getText().toString());
                }

                break;

            case R.id.espCardiologia:
                if (checked) {
                    cardiologia.setChecked(false);
                    listaCheckbox.remove(cardiologia.getText().toString());
                } else {
                    cardiologia.setChecked(true);
                    listaCheckbox.add(cardiologia.getText().toString());
                }

                break;

        }

    }
}
