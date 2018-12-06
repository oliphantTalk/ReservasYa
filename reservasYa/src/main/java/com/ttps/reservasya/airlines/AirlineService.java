package com.ttps.reservasya.airlines;

import com.ttps.reservasya.airlines.flights.Flight;
import com.ttps.reservasya.airlines.flights.FlightRepository;
import com.ttps.reservasya.services.modelcrud.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
