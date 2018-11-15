package com.ttps.reservasya.models.businessentity;

import com.ttps.reservasya.models.businessitem.Fligth;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AIRLINE")
public class Airline implements Serializable {

    private long id;
    private String name;
    private List<Fligth> fligths;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection(targetClass = Fligth.class)
    public List<Fligth> getFligths() {
        return fligths;
    }

    public void setFligths(List<Fligth> fligths) {
        this.fligths = fligths;
    }




}
