package com.ttps.reservasya.controllers.panel.form;

import com.ttps.reservasya.models.LocalParameters;
import org.apache.tomcat.jni.Local;

/**
 * @author nahuelbarrena on 06/01/19
 */
public class LocalParamsForm {

    private int gapMax;
    private int factorServicioVuelo;
    private double factorVueloEscala;
    private double factorDevolucion;
    private double pesosPorPunto;
    private double puntosPorPeso;
    private double firstClassRate;
    private double businessClassRate;

    public LocalParamsForm(){}

    public LocalParamsForm(LocalParameters localParameters) {
        gapMax = localParameters.getGapMax();
        factorDevolucion = localParameters.getFactorDevolucion();
        factorServicioVuelo = localParameters.getFactorServicioVuelo();
        factorVueloEscala = localParameters.getFactorVueloEscala();
        pesosPorPunto = localParameters.getPesosPorPunto();
        puntosPorPeso = localParameters.getPuntosPorPeso();
        businessClassRate = localParameters.getBusinessClassRate();
        firstClassRate = localParameters.getFirstClassRate();
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
