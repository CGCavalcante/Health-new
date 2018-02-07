package com.maishealth.maishealth.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maishealth.maishealth.R;
import com.maishealth.maishealth.infra.GuiUtil;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.negocio.Servicos;
import com.maishealth.maishealth.usuario.negocio.ServicosConsulta;
import com.maishealth.maishealth.usuario.negocio.ServicosMedico;
import com.maishealth.maishealth.usuario.negocio.ServicosPaciente;
import com.maishealth.maishealth.usuario.negocio.ServicosPessoa;

public class DetalhesMedico extends AppCompatActivity {
    private ImageView fotoMedico;
    private TextView nomeMedico;
    private TextView especMedico;
    private TextView dataCons;
    private TextView turnoCons;
    private TextView crm;
    private String data;
    private String turno;
    private String idmedicoS;
    private String diaSemana;
    private Medico medico;
    private Pessoa pessoaMedico;
    private String nomeMedicoString;
    private String especString;
    private String crmString;
    private ServicosMedico servicosMedico;
    private ServicosPessoa servicosPessoa;
    private ServicosConsulta servicosConsulta;
    private ServicosPaciente servicosPaciente;
    private Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medico);

        fotoMedico = findViewById(R.id.fotoMedico);
        dataCons = findViewById(R.id.datacons);
        turnoCons = findViewById(R.id.turnocons);
        nomeMedico  = findViewById(R.id.txtNomeMedico);
        especMedico = findViewById(R.id.txtEspecMedico);
        crm = findViewById(R.id.idTxtCrm);
        confirmar = findViewById(R.id.btnConfirmarMarcar);


        Intent intent = getIntent();
        data =  intent.getStringExtra("data1");
        turno =  intent.getStringExtra("turno1");
        idmedicoS = intent.getStringExtra("idmedico");
        diaSemana = intent.getStringExtra("diaSemana1");
        final long idmedico = Long.parseLong(idmedicoS);

        servicosMedico = new ServicosMedico(getApplicationContext());
        servicosPessoa = new ServicosPessoa(getApplicationContext());
        servicosConsulta = new ServicosConsulta(getApplicationContext());
        servicosPaciente = new ServicosPaciente(getApplicationContext());

        medico = servicosMedico.getMedico(idmedico);
        pessoaMedico = servicosPessoa.searchPessoaByIdUsuario(medico.getIdUsuario());
        nomeMedicoString = pessoaMedico.getNome();
        especString = medico.getEspecialidade();
        crmString = medico.getCrm();

        GuiUtil.myToast(getApplicationContext(), "data" + data + "\nturno" + turno + "\nidmedico" + idmedico + "\ndiaSemana " + diaSemana);

        dataCons.setText(data);
        turnoCons.setText(turno);
        nomeMedico.setText(nomeMedicoString);
        especMedico.setText(especString);
        crm.setText(crmString);

        // esse metodo gera consultas para o dia selecionado pelo paciente
        // ainda precisa por uma validação aqui, pq essa criação só pode ocorrer 1 vez.
        // por hora tá infinita a quantidades de vezes que pode colocar.
        servicosConsulta.gerarConsultas(idmedico, turno, data, diaSemana);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifica se a consulta existe para poder atualizar passando o id do paciente
                // o status disponivel  do enum status da consulta voltou para auxilar nas listagens das consultas.
                Consulta consulta = servicosConsulta.getConsulta(idmedico, turno, data);
                if (consulta!= null){
                    // marca consulta para o paciente, por hora o paciente pode marcar quantas consultas ele quiser
                    // por isso temos que por uma validação aqui para ver se o paciente já tem uma consulta marcada
                    // para esse medico, dia e turno.
                    servicosPaciente.marcarConsulta(idmedico,data,turno);
                }

            }
        });


        /*DadosMedico obj = (DadosMedico) getIntent().getExtras().getSerializable("objeto");
        nomeMedico.setText(obj.getNome());
        especMedico.setText(obj.getEspecialidade());
        fotoMedico.setImageResource(obj.getImagem());*/
    }
}
