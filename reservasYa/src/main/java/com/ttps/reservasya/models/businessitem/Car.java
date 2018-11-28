package com.ttps.reservasya.models.businessitem;

import com.ttps.reservasya.models.businessentity.Agency;

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
    private String patent;
    private Agency agency;
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    @ManyToOne(cascade = CascadeType.ALL)
    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
