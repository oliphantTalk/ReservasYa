package com.ttps.reservasya.controllers.searchbox;

public class SearchHotelForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    private String to;
    private String dateFrom;
    private String dateTo;
    private int passenger;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
