package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessItem.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FligthRepository extends JpaRepository<Car, Long> {
}
