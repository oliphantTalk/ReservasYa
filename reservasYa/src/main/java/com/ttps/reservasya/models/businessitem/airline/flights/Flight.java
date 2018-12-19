package com.ttps.reservasya.models.businessitem.airline.flights;

import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.BusinessItem;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private int gapMax = 1;
    private Airline airline;
    @Embedded
    private List<FlightSeat> seats;
    private int economicCapacity;
    private int businessCapacity;
    private int firstCapacity;


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

    public int getEconomicCapacity() {
        return economicCapacity;
    }

    public void setEconomicCapacity(int economicCapacity) {
        this.economicCapacity = economicCapacity;
    }

    public int getBusinessCapacity() {
        return businessCapacity;
    }

    public void setBusinessCapacity(int businessCapacity) {
        this.businessCapacity = businessCapacity;
    }

    public int getFirstCapacity() {
        return firstCapacity;
    }

    public void setFirstCapacity(int firstCapacity) {

        this.firstCapacity = firstCapacity;
    }

    public int getGapMax() {
        return gapMax;
    }

    public void setGapMax(int gapMax) {
        this.gapMax = gapMax;
    }

    @PrePersist
    public void configDuration(){
        String dur = Duration.between(this.departureTime, this.arrivalTime).toString();
        String duracion = dur.split("PT")[1];
        String horas = duracion.substring(0, 2);
        if(duracion.contains("M")){
            duracion = duracion.substring(0, 6);
            this.setDuration(duracion.substring(0, 2) + "h " + duracion.substring(3, 5) + "m" );
        }
        else if (duracion.length() > 2){
            this.setDuration(duracion.substring(0, 2) + "h " + "00m" );
        }
        else {
            this.setDuration(duracion.substring(0, 1) + "h " + "00m" );
            horas = duracion.substring(0, 1);
        }
        this.setDays(String.valueOf((Integer.valueOf(horas) / 24)));
        if(getEconomicCapacity() == 0 && getBusinessCapacity() == 0 && getFirstCapacity() == 0) {
            setEconomicCapacity(this.seats.stream().filter(s -> s.getSeatClass().equals(SeatClass.ECONOMIC)).collect(Collectors.toList()).size());
            setBusinessCapacity(this.seats.stream().filter(s -> s.getSeatClass().equals(SeatClass.BUSINESS)).collect(Collectors.toList()).size());
            setFirstCapacity(this.seats.stream().filter(s -> s.getSeatClass().equals(SeatClass.ECONOMIC)).collect(Collectors.toList()).size());
        }
    }

}
