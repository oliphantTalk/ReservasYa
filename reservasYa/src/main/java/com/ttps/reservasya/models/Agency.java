package com.ttps.reservasya.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="agency")
public class Agency implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    private String name;


}
