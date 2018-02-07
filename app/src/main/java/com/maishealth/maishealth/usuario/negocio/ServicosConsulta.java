package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;
import com.maishealth.maishealth.usuario.dominio.HorarioMedico;
import com.maishealth.maishealth.usuario.persistencia.ConsultaDAO;
import com.maishealth.maishealth.usuario.persistencia.HorarioMedicoDAO;

public class ServicosConsulta {

    private ConsultaDAO consultaDAO;
    private HorarioMedicoDAO horarioMedicoDAO;

    public ServicosConsulta(Context context) {
        consultaDAO = new ConsultaDAO(context);
        horarioMedicoDAO = new HorarioMedicoDAO(context);
    }

    private void cadastrarConsulta (Consulta consulta){
        consultaDAO.inserirConsulta(consulta);
    }

    public void cadastrarConsulta (long idMedico, String data, String turno){
        Consulta consulta = new Consulta();
        consulta.setTurno(turno);
        consulta.setData(data);
        consulta.setIdMedico(idMedico);
        consulta.setStatus(EnumStatusConsulta.DISPONIVEL.toString());
        cadastrarConsulta(consulta);
    }

    public void atualizarConsulta (long idMedico, String data, String turno ,long idPaciente){
        Consulta consulta = consultaDAO.getConsultaDisponivel(idMedico, data, turno);
         if (consulta != null){
             consulta.setTurno(turno);
             consulta.setData(data);
             consulta.setIdMedico(idMedico);
             consulta.setIdPaciente(idPaciente);
             consulta.setStatus(EnumStatusConsulta.EMANDAMENTO.toString());
             consultaDAO.atualizarConsulta(consulta);
         }

    }

    public void gerarConsultas(long idMedico, String turno, String data, String diaSemana){
        HorarioMedico horarioMedico = horarioMedicoDAO.getHorarioMedico(idMedico, diaSemana, turno);
        if (horarioMedico != null){
           long vagas = horarioMedico.getVagas() ;
           //int vagas = Integer.getInteger(vagasString);
           int contador = 0;
            while (contador < vagas ){
                cadastrarConsulta(idMedico,data,turno);
                contador++;
            }
        }

    }

    public Consulta getConsulta(long idMedico, String turno, String data){
        Consulta consulta = consultaDAO.getConsultaDisponivel(idMedico, data, turno);
        return consulta;
    }

}
