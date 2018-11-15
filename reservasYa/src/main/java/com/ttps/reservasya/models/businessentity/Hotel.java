package com.ttps.reservasya.models.businessentity;

import com.ttps.reservasya.models.businessitem.Room;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "HOTEL")
public class Hotel implements Serializable {

    private long id;
    private String name;
    private List<Room> rooms;
    private int stars;
    private int roomsAvailable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection(targetClass = Room.class)
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Column(name = "ROOMS_AVAILABLE")
    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }
}
