package com.ttps.reservasya.repository.airline;

import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<List<Flight>> findFlightsByDepartureDateGreaterThanEqualAndFromAndTo(LocalDate departureDate, String from, String to);
    Optional<List<Flight>> findFlightsByFromAndTo(String from, String to);
}
