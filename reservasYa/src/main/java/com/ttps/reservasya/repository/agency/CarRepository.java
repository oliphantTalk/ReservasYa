package com.ttps.reservasya.repository.agency;

import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
