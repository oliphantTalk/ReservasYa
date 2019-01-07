package com.ttps.reservasya.models.businessitem.hotel;

import com.ttps.reservasya.models.businessitem.BusinessItem;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROOM")
@DiscriminatorValue("ROOM")

public class Room extends BusinessItem implements Serializable {

    private Long id;
    private String roomId;
    private int beds;
    private Hotel hotel;


    public Room(){
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


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @ManyToOne
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
