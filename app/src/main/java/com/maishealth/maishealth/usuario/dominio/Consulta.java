package com.maishealth.maishealth.usuario.dominio;


public class Consulta {
    private long idPaciente;
    private long id_data_horario;
    private String status;
    private long id;

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getId_data_horario() {
        return id_data_horario;
    }

    public void setId_data_horario(long id_data_horario) {
        this.id_data_horario = id_data_horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() { return id;}

    public void setId(long id) { this.id = id; }
}
