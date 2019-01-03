package com.ttps.reservasya.repository.agency;

import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
