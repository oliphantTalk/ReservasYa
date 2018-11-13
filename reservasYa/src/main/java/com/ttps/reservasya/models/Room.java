package com.ttps.reservasya.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROOM")
public class Room extends BusinessEntityItem implements Serializable {

    private long id;
    private int beds;
    private Hotel hotel;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
