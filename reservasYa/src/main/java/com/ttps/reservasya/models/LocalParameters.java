package com.ttps.reservasya.models;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Table
public class LocalParameters {



    private Long id;
    private static int gapMax = 4;
    private static int factorServicioVuelo = 10;
    private static double factorVueloEscala = 0.5;
    private static double factorDevolucion = 0.1;
    private static double pesosPorPunto = 0.5;
    private static double puntosPorPeso = 0.75;
    private static double firstClassRate = 0.5;
    private static double businessClassRate = 0.25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGapMax() {
        return gapMax;
    }

    public void setGapMax(int gapMax) {
        this.gapMax = gapMax;
    }

    public int getFactorServicioVuelo() {
        return factorServicioVuelo;
    }

    public void setFactorServicioVuelo(int factorServicioVuelo) {
        this.factorServicioVuelo = factorServicioVuelo;
    }

    public double getFactorVueloEscala() {
        return factorVueloEscala;
    }

    public void setFactorVueloEscala(double factorVueloEscala) {
        this.factorVueloEscala = factorVueloEscala;
    }

    public double getFactorDevolucion() {
        return factorDevolucion;
    }

    public void setFactorDevolucion(double factorDevolucion) {
        this.factorDevolucion = factorDevolucion;
    }

    public double getPesosPorPunto() {
        return pesosPorPunto;
    }

    public void setPesosPorPunto(double pesosPorPunto) {
        this.pesosPorPunto = pesosPorPunto;
    }

    public double getPuntosPorPeso() {
        return puntosPorPeso;
    }

    public void setPuntosPorPeso(double puntosPorPeso) {
        this.puntosPorPeso = puntosPorPeso;
    }

    public double getFirstClassRate() {
        return firstClassRate;
    }

    public void setFirstClassRate(double firstClassRate) {
        this.firstClassRate = firstClassRate;
    }

    public double getBusinessClassRate() {
        return businessClassRate;
    }

    public void setBusinessClassRate(double businessClassRate) {
        this.businessClassRate = businessClassRate;
    }
}
