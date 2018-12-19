package com.ttps.reservasya.models;

import javax.persistence.*;

@Entity
@Table
public class LocalParameters {

    private Long id;
    public static int gapMax = 4;
    public static int factorServicioVuelo = 10;
    public static double factorVueloEscala = 0.5;
    public static double factorDevolucion = 0.1;
    public static double pesosPorPunto = 0.5;
    public static double puntosPorPeso = 0.75;
    public static double firstClassRate = 0.5;
    public static double businessClassRate = 0.25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static int getGapMax() {
        return gapMax;
    }

    public static int getFactorServicioVuelo() {
        return factorServicioVuelo;
    }

    public static double getFactorVueloEscala() {
        return factorVueloEscala;
    }

    public static double getFactorDevolucion() {
        return factorDevolucion;
    }

    public static double getPesosPorPunto() {
        return pesosPorPunto;
    }

    public static double getPuntosPorPeso() {
        return puntosPorPeso;
    }

    public static double getFirstClassRate() {
        return firstClassRate;
    }

    public static double getBusinessClassRate() {
        return businessClassRate;
    }
}
