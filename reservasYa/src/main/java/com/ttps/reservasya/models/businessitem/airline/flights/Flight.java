package com.ttps.reservasya.models.businessitem.airline.flights;

import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.BusinessItem;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
@DiscriminatorValue("FLIGHT")

public class Flight extends BusinessItem implements Serializable {

    private Long id;
    private String flyCode;
    private String from;
    private String to;
    private LocalDate departureDate;
    private LocalDateTime departureTime;
    private LocalDate arrivalDate;
    private LocalDateTime arrivalTime;
    private String duration;
    private String days;
    private int scales = 1;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public int getScales() {
        return scales;
    }

    public void setScales(int scales) {
        this.scales = scales;
    }

    @ManyToOne
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @PrePersist
    public void configDuration(){
        String dur = Duration.between(this.departureTime, this.arrivalTime).toString();
        String duracion = dur.split("PT")[1].substring(0, 6);
        this.setDuration(duracion.substring(0, 2) + "h " + duracion.substring(3, 5) + "m" );
        this.setDays(String.valueOf((Integer.valueOf(duracion.substring(0, 2)) / 24)));
    }
}
