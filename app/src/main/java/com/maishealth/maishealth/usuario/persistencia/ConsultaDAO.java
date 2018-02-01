package com.maishealth.maishealth.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maishealth.maishealth.infra.DataBase;
import com.maishealth.maishealth.usuario.dominio.Consulta;
import com.maishealth.maishealth.usuario.dominio.EnumStatusConsulta;

public class ConsultaDAO {
    private SQLiteDatabase liteDatabase;
    private DataBase dataBaseHelper;

    public ConsultaDAO(Context context) {
        dataBaseHelper = new DataBase(context);
    }

    public long inserirConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaIdPaciente, idPaciente);

        String colunaIdDataHorario = DataBase.ID_EST_DATA_HORARIO;
        long idDataHorario = consulta.getId_data_horario();
        values.put(colunaIdDataHorario, idDataHorario);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        long id = liteDatabase.insert(tabela, null, values);

        liteDatabase.close();

        return id;
    }

    public long atualizarConsulta(Consulta consulta){
        liteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_CONSULTA;

        String colunaIdDataHorario = DataBase.ID_EST_DATA_HORARIO;
        long idDataHorario = consulta.getId_data_horario();
        values.put(colunaIdDataHorario, idDataHorario);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        String status = consulta.getStatus();
        values.put(colunaStatus, status);

        String colunaPaciente = DataBase.ID_EST_PACIENTE_CON;
        long idPaciente = consulta.getIdPaciente();
        values.put(colunaPaciente, idPaciente);

        String whereClause = DataBase.ID_CONSULTA + " = ? ";
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(consulta.getId());

        long id = liteDatabase.update(tabela, values, whereClause, parametros);

        liteDatabase.close();

        return id;
    }

    private Consulta criarConsulta(Cursor cursor){

        String colunaIdConsulta = DataBase.ID_CONSULTA;
        int indexColunaIdConsulta = cursor.getColumnIndex(colunaIdConsulta);
        long idConsulta = cursor.getInt(indexColunaIdConsulta);

        String colunaIdPaciente = DataBase.ID_EST_PACIENTE_CON;
        int indexColunaIdPaciente = cursor.getColumnIndex(colunaIdPaciente);
        long idPaciente = cursor.getInt(indexColunaIdPaciente);

        String colunaIdDataHorario = DataBase.ID_EST_DATA_HORARIO;
        int indexColunaIdDataHorario = cursor.getColumnIndex(colunaIdDataHorario);
        long idDataHorario = cursor.getInt(indexColunaIdDataHorario);

        String colunaStatus = DataBase.STATUS_CONSULTA;
        int indexColunaStatus = cursor.getColumnIndex(colunaStatus);
        String status = cursor.getString(indexColunaStatus);

        Consulta consulta = new Consulta();

        consulta.setIdPaciente(idPaciente);
        consulta.setId_data_horario(idDataHorario);
        consulta.setStatus(status);
        consulta.setId(idConsulta);

        return consulta;
    }

    private Consulta getConsulta(String query, String[] argumentos){
        liteDatabase = dataBaseHelper.getReadableDatabase();

        Cursor cursor = liteDatabase.rawQuery(query, argumentos);

        Consulta consulta = null;

        if(cursor.moveToNext()){
            consulta = criarConsulta(cursor);
        }

        cursor.close();
        liteDatabase.close();

        return consulta;
    }

    public Consulta getConsultaDisponivel(long idPaciente, long idDataHorario, long idConsulta) {
        String query = "SELECT * FROM " + DataBase.TABELA_CONSULTA +
                " WHERE " + DataBase.ID_EST_PACIENTE_CON + " LIKE ?" +
                " AND " + DataBase.ID_EST_DATA_HORARIO + " LIKE ?" +
                    " AND " + DataBase.ID_CONSULTA + " LIKE ?";

        String idPacienteString = Long.toString(idPaciente);
        String idDataHorarioString = Long.toString(idDataHorario);
        String idConsultaString   = Long.toString(idConsulta);
        String statusConsulta = EnumStatusConsulta.DISPONIVEL.toString();

        String[] argumentos = {idPacienteString, idDataHorarioString, idConsultaString, statusConsulta};

        return this.getConsulta(query, argumentos);
    }

}
