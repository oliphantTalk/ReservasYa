package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessitem.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
