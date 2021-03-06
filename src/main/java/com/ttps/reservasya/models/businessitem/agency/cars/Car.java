package com.ttps.reservasya.models.businessitem.agency.cars;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.businessitem.agency.Agency;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
@DiscriminatorValue("CAR")
public class Car extends BusinessItem implements Serializable {

    private Long id;
    private String description;
    private String model;
    private int year;
    private int capacity;
    private String patent;
    private Agency agency;

    public Car(){
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
