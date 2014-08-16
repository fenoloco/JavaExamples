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
    @CustomValidator(3)
    private long ventanas;

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

    public long getVentanas() {
        return ventanas;
    }

    public void setVentanas(long ventanas) {
        this.ventanas = ventanas;
    }

    @Override
    public String toString() {
        return "Car{" + "marca=" + marca + ", modelo=" + modelo + ", puertas=" + puertas + '}';
    }

}
