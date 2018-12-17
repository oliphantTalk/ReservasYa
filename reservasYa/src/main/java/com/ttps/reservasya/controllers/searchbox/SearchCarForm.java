package com.ttps.reservasya.controllers.searchbox;

import com.ttps.reservasya.models.businessitem.agency.Agency;

public class SearchCarForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String pickup;
    private String retrieve;
    private String dateFrom;
    private String dateTo;
    private int passenger;

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getRetrieve() {
        return retrieve;
    }

    public void setRetrieve(String retrieve) {
        this.retrieve = retrieve;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }
}
