package com.ttps.reservasya.controllers.checkout;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckoutForm {

    private String passengerName;
    private String passengerLastName;
    private int dni;
    private int securityCode;
    private String creditCard;
    private String expirationDate;
    private int pointsToConvert;
    private Long tId;

    public CheckoutForm(){};
    public CheckoutForm(Long tId){
        super();
        this.tId = tId;
    }

}
