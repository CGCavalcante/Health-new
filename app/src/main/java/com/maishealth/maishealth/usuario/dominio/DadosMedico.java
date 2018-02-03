package com.maishealth.maishealth.usuario.dominio;

import java.io.Serializable;

/**
 * Created by Wenderson de Souza on 01/02/2018.
 */

public class DadosMedico implements Serializable {
    private int id;
    private String nome;
    private String especialidade;
    private int imagem;

    public DadosMedico(int id, String nome, String especialidade, int imagem) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
