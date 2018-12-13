package com.ttps.reservasya.services.airlines;

import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.repository.airline.AirlineRepository;
import com.ttps.reservasya.repository.airline.FlightRepository;
import com.ttps.reservasya.services.BasicCrudService;
import com.ttps.reservasya.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirlineService extends BasicCrudService<Airline, AirlineRepository> {

    private FlightRepository flightRepository;

    public AirlineService(){};
    @Autowired
    public AirlineService(AirlineRepository airlineRepository, FlightRepository flightRepository){
        super(airlineRepository);
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> findFligth(Long id){
        return flightRepository.findById(id);
    }

    public List<Flight> findFligths(){
        return flightRepository.findAll();
    }

    public Flight createFligth(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> createFlights(List<Flight> flights){
        return flightRepository.saveAll(flights);
    }

    public Flight updateFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id){
        flightRepository.deleteById(id);
    }

    public List<Flight> findOneWayFlights(String departureDate, String from, String to){
        return this.flightRepository.findFlightsByDepartureDateGreaterThanEqualAndFromAndTo(DateParser.parse(departureDate), from, to).orElse(new ArrayList<>());
    }

    public List<Flight> findFlightFromAndTo(String from, String to){
        return this.flightRepository.findFlightsByFromAndTo(from, to).orElse(new ArrayList<>());
    }

}
