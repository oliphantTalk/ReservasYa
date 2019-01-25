package com.ttps.reservasya.models.businessitem.airline;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AIRLINE")
public class Airline implements Serializable {

    private long id;
    @NotNull
    private String name;
    @NotNull
    @JsonProperty("short_name")
    private String shortName;
    private List<Flight> flights;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NaturalId(mutable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "airline")
    @ElementCollection(targetClass = Flight.class)
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }




}
