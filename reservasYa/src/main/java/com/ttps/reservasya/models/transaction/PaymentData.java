package com.ttps.reservasya.models.transaction;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class PaymentData implements Serializable {

    private String passengerName;
    private String passengerLastName;
    private String dni;
    private String cashAmount;
    private String cashByPoints;
    private String creditCard;
    private String paymentDate = LocalDateTime.now().toString();
    private String paymentStatus = PaymentStatus.PENDING.toString();

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

    public String getCashByPoints() {
        return cashByPoints;
    }

    public void setCashByPoints(String cashByPoints) {
        this.cashByPoints = cashByPoints;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void approve(){
        this.setPaymentStatus(PaymentStatus.APPROVED.name());
        this.setPaymentDate(LocalDateTime.now().toString());
    }

    public void reject(){
        this.setPaymentStatus(PaymentStatus.REJECTED.name());
        this.setPaymentDate(LocalDateTime.now().toString());
    }
}
