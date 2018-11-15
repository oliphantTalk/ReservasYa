package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessEntity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
