package com.example.ctrlautomacao.model;

public class Ambiente {
    private int idAmbiente;
    private String nmAmbiente;

    //metodo toString() para o adapter do spinner
    @Override
    public String toString() {
        return this.nmAmbiente;
    }

    public String getNmAmbiente() {
        return nmAmbiente;
    }

    public void setNmAmbiente(String nmAmbiente) {
        this.nmAmbiente = nmAmbiente;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }
}
