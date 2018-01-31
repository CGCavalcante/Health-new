package com.maishealth.maishealth.usuario.negocio;

import android.content.Context;
import android.database.Cursor;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Pessoa;
import com.maishealth.maishealth.usuario.persistencia.MedicoPostoDAO;
import com.maishealth.maishealth.usuario.persistencia.PessoaDAO;
import com.maishealth.maishealth.usuario.persistencia.PostoDAO;
import com.maishealth.maishealth.usuario.persistencia.UsuarioDAO;

import java.util.ArrayList;

/**
 * Created by Wenderson de Souza on 31/01/2018.
 */

public class ServicosPosto {
    public PessoaDAO pessoaDAO;
    private PostoDAO postoDAO;
    private MedicoPostoDAO medicoPostoDAO;

    public ServicosPosto(Context context) {
        postoDAO = new PostoDAO(context);
        medicoPostoDAO = new MedicoPostoDAO(context);
        pessoaDAO = new PessoaDAO(context);
    }

    /*public ArrayList<String> getPessoaByMedico(ArrayList<Medico> medicos){
        ArrayList<String> pessoasMedico = new ArrayList<String>();
        for (Medico medico :medicos){
            long idUsuario = medico.getIdUsuario();
            Pessoa pessoa = pessoaDAO.getPessoaByIdUsuario(idUsuario);

            pessoasMedico.add(pessoa.getNome());

        }

        return pessoasMedico;
    }

    public ArrayList<String> getEspecByMedico(ArrayList<Medico> medicos){
        ArrayList<String> especMedico = new ArrayList<String>();
        for (Medico medico : medicos){
            String espec = medico.getEspecialidade();
            especMedico.add(espec);

        }

        return especMedico;

    }*/

    public ArrayList<String> returnNomeMedicos(long id) {
        /*medicoPostoDAO.inserirMedicoPosto(1,1);
        medicoPostoDAO.inserirMedicoPosto(2,1);
        medicoPostoDAO.inserirMedicoPosto(3,1);
        medicoPostoDAO.inserirMedicoPosto(4,1);

        //ArrayList<Medico> medicos = medicoPostoDAO.getMedicosByPosto(id);

        //ArrayList<String> pessoasMedico = getPessoaByMedico(medicos);

        //ArrayList<String> especMedico = getEspecByMedico(medicos);*/

        ArrayList<String> especMedico = new ArrayList<String>();

        especMedico.add("ffff");
        especMedico.add("ffff");
        especMedico.add("ffff");
        especMedico.add("ffff");

        return especMedico;
    }

}
