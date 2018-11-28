package com.ttps.reservasya.models.businessitem;

import com.ttps.reservasya.models.businessentity.Airline;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
@DiscriminatorValue("FLIGHT")
public class Flight extends BusinessItem implements Serializable {

    private Long id;
    private String flyCode;
    private String from;
    private String to;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String duration;
    private int scales = 1;
    private Double price;
    private Airline airline;
    @Embedded
    private List<FlightSeat> seats;

    public Flight() {
        super();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "ARRIVAL_DATE")
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
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

    @ManyToOne(cascade = CascadeType.ALL)
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }


    @ElementCollection(targetClass = FlightSeat.class, fetch = FetchType.EAGER)
    public List<FlightSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<FlightSeat> seats) {
        this.seats = seats;
    }

    @PrePersist
    public void configDuration(){
        String duration = Duration.between(this.departureDate, this.arrivalDate).toString();
        this.setDuration(duration.split("PT")[1]);
    }
}
