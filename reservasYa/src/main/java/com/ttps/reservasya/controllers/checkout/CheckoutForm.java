package com.ttps.reservasya.controllers.checkout;

public class CheckoutForm {

    private String passengerName;
    private String passengerLastName;
    private int dni;
    private int securityCode;
    private String creditCard;
    private String expirationDate;
    private int pointsToConvert;

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPointsToConvert() {
        return pointsToConvert;
    }

    public void setPointsToConvert(int pointsToConvert) {
        this.pointsToConvert = pointsToConvert;
    }
}
