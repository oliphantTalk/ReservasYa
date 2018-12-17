package com.ttps.reservasya.controllers.searchbox;

import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;

public class SearchFlyForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String from;
    private String to;
    private String departureDate;
    private String arrivalDate;
    private String clase;
    private int passenger;


    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }
}
