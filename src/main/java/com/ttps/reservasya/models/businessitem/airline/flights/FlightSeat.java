package com.ttps.reservasya.models.businessitem.airline.flights;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class FlightSeat implements Serializable {

    private String seatId = RandomStringUtils.randomAlphanumeric(10);
    private SeatClass seatClass;
    private Double priceClass;



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

    public Double getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(Double priceClass) {
        this.priceClass = priceClass;
    }
}
