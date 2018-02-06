package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesMedico extends AppCompatActivity {
    private ImageView fotoMedico;
    private TextView nomeMedico;
    private TextView especMedico;
    private TextView dataCons;
    private TextView turnoCons;
    private String data;
    private String turno;
    private String idmedicoS;
    /*private Medico medico;
    private Pessoa pessoaMedico;

    private ServicosMedico servicosMedico = new ServicosMedico(getApplicationContext());
    private ServicosPessoa servicosPessoa = new ServicosPessoa(getApplicationContext());*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        nomeMedico = findViewById(R.id.nomeMedico);
        especMedico =  findViewById(R.id.especMedico);
        dataCons = findViewById(R.id.datacons);
        turnoCons = findViewById(R.id.turnocons);


        Intent intent = getIntent();
        data =  intent.getStringExtra("data1");
        turno =  intent.getStringExtra("turno1");
        idmedicoS = intent.getStringExtra("idmedico");
        long idmedico = Long.parseLong(idmedicoS);

        /*medico = servicosMedico.getMedico(idmedico);
        pessoaMedico = servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());*/


        GuiUtil.myToast(getApplicationContext(), "data" + data + "\nturno" + turno + "\nidmedico" + idmedico);

        dataCons.setText(data);
        turnoCons.setText(turno);

        /*nomeMedico.setText(pessoaMedico.getNome());
        especMedico.setText(medico.getEspecialidade());*/


        /*DadosMedico obj = (DadosMedico) getIntent().getExtras().getSerializable("objeto");
        nomeMedico.setText(obj.getNome());
        especMedico.setText(obj.getEspecialidade());
        fotoMedico.setImageResource(obj.getImagem());*/
    }
}
