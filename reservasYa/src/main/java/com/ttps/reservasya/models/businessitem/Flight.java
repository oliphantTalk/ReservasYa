package com.ttps.reservasya.models.businessitem;

import com.ttps.reservasya.models.businessentity.Airline;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "FLIGTH")
public class Flight extends BusinessItem implements Serializable {

    private Long id;
    private String flyCode;
    private String from;
    private String to;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalTime duration;
    private int scales = 1;
    private Double price;
    private Airline airline;
    private List<FlightSeat> seats;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NaturalId
    @Column(name = "FLY_CODE")
    public String getFlyCode() {
        return flyCode;
    }

    public void setFlyCode(String flyCode) {
        this.flyCode = flyCode;
    }

    @Column(name = "CITY_FROM")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Column(name = "CITY_TO")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Column(name = "DEPARTURE_DATE")
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "ARRIVAL_DATE")
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Column(name = "DEPARTURE_TIME")
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Column(name = "ARRIVAL_TIME")
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public int getScales() {
        return scales;
    }

    public void setScales(int scales) {
        this.scales = scales;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "AIRLINE_ID")
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }


    @ElementCollection(targetClass = FlightSeat.class)
    public List<FlightSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<FlightSeat> seats) {
        this.seats = seats;
    }
}
