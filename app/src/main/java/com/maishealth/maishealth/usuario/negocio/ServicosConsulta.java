package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;

public class ServicosConsulta {

    private ConsultaDAO consultaDAO;

    public ServicosConsulta(Context context) {
        consultaDAO = new ConsultaDAO(context);
    }

    private void cadastrarConsulta (Consulta consulta){
        consultaDAO.inserirConsulta(consulta);
    }

    public void cadastrarConsulta (long idConsulta){
        Consulta consulta = new Consulta();
        consulta.setId(idConsulta);

        cadastrarConsulta(consulta);
    }

}
