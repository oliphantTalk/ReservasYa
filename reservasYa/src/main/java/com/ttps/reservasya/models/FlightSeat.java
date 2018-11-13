package com.ttps.reservasya.models;



import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class FlightSeat implements Serializable {



    private String seatId;
    private SeatClass seatClass;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }
}
