package com.ttps.reservasya.repository.airline;

import com.ttps.reservasya.models.businessitem.airline.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
