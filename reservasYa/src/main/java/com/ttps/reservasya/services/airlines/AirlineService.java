package com.ttps.reservasya.services.airlines;

import com.ttps.reservasya.error.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;
import com.ttps.reservasya.repository.airline.AirlineRepository;
import com.ttps.reservasya.repository.airline.FlightRepository;
import com.ttps.reservasya.services.BasicCrudService;
import com.ttps.reservasya.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineService extends BasicCrudService<Airline, AirlineRepository> {

    private FlightRepository flightRepository;

    public AirlineService(){};
    @Autowired
    public AirlineService(AirlineRepository airlineRepository, FlightRepository flightRepository){
        super(airlineRepository);
        this.flightRepository = flightRepository;
    }

    public Flight findFligth(Long id){
        // esta mal esta excepcion
        return flightRepository.findById(id).orElseThrow(UserNotFoundException::new);
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

    public List<Flight> findOneWayFlights(String departureDate, String from, String to, SeatClass seatClass){
        List<Flight> flights = this.flightRepository.findFlightsByDepartureDateGreaterThanEqualAndFromAndToAndGapMaxLessThanOrderByAirline(DateParser.parse(departureDate), from, to, LocalParameters.gapMax).orElse(new ArrayList<>());
        flights.forEach(f -> f.setSeats(f.getSeats().stream().filter(s -> s.getSeatClass().equals(seatClass)).collect(Collectors.toList())));
        return flights;

    }

    public Flight getFlightBySeatClass(Long id,  String seatClass) {
        Flight flight = this.findFligth(id);
        flight.setSeats(flight.getSeats().stream().filter(s -> SeatClass.valueOf(seatClass).equals(s.getSeatClass())).collect(Collectors.toList()));
        return flight;
    }

}
