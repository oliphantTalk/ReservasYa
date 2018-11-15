package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessItem.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
