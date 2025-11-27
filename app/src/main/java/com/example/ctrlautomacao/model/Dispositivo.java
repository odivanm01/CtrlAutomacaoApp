package com.example.ctrlautomacao.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Dispositivo {
    //atributos
    private int id;
    private String estado;
    private String grupo;
    private String tipo;
    private int idAmbiente;

    //construtores
    public Dispositivo() {
        this.id = 0;
        this.estado = "";
        this.grupo = "";
        this.tipo = "";
        this.idAmbiente = 0;
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Dispositivo (JSONObject jp) {
        try {
            this.setId(jp.getInt("ipDispositivo"));
            this.setEstado(jp.getString("Estado"));
            this.setGrupo(jp.getString("Grupo"));
            this.setTipo(jp.getString("Tipo"));
            this.setIdAmbiente(jp.getInt("idAmbiente"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("ipDispositivo", this.id);
            json.put("Estado", this.estado);
            json.put("Grupo", this.grupo);
            json.put("Tipo", this.tipo);
            json.put("idAmbiente", this.idAmbiente);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    //metodos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) throws Exception {
        if (estado.length() > 0) {
            this.estado = estado;
        } else {
            throw new Exception("Estado invalido");
        }

    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }
}
