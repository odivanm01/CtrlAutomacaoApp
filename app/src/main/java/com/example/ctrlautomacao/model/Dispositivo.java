package com.example.ctrlautomacao.model;

public class Dispositivo {
    //atributos
    private String estado;
    private String grupo;
    private String tipo;
    private int idAmbiente;

    //metodos

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
