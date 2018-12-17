package com.ttps.reservasya.models.businessitem.agency;

import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="agency")
public class Agency implements Serializable {


    private Long id;
    private String name;
    private String city;
    private List<Car> cars = new ArrayList<>();


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NaturalId(mutable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "agency", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ElementCollection(targetClass = Car.class)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
