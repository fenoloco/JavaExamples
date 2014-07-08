package com.myworkday.cditests.model;

import com.myworkday.cditests.validator.CustomValidator;

/**
 *
 * @author Mauri
 */
public class Car {

    private String marca;
    private String modelo;
    @CustomValidator(2)
    private long puertas;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public long getPuertas() {
        return puertas;
    }

    public void setPuertas(long puertas) {
        this.puertas = puertas;
    }

    @Override
    public String toString() {
        return "Car{" + "marca=" + marca + ", modelo=" + modelo + ", puertas=" + puertas + '}';
    }

}
