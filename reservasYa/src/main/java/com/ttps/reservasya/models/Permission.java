package com.ttps.reservasya.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "PERMISSION")
public class Permission implements Serializable {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
