package com.ttps.reservasya.models.businessitem;

import com.ttps.reservasya.models.businessentity.Agency;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
public class Car extends BusinessItem implements Serializable {

    private Long id;
    private String model;
    private int year;
    private String patent;
    private Agency agency;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    @ManyToOne
    @JoinColumn(name = "agency_id")
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
