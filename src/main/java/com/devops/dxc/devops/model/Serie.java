package com.devops.dxc.devops.model;

import java.io.Serializable;
import java.util.Date;

public class Serie implements Serializable {

    private static final long serialVersionUID = -2L;

    private Date fecha;
    private Double valor;

    public Serie() {
    }

    public Serie(Date fecha, Double valor) {
        this.fecha = fecha;
        this.valor = valor; 
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}