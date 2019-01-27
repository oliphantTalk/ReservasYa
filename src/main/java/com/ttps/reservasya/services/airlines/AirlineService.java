package com.ttps.reservasya.services.airlines;

import com.ttps.reservasya.controllers.panel.form.ABMAirlineForm;
import com.ttps.reservasya.controllers.panel.form.ABMFlightForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
import com.ttps.reservasya.error.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.models.businessitem.airline.flights.FlightSeat;
import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;
import com.ttps.reservasya.repository.airline.AirlineRepository;
import com.ttps.reservasya.repository.airline.FlightRepository;
import com.ttps.reservasya.services.BasicCrudService;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.utils.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineService extends BasicCrudService<Airline, AirlineRepository> {

    private FlightRepository flightRepository;

    private LocalParametersService localParametersService;

    public AirlineService(){};
    @Autowired
    public AirlineService(AirlineRepository airlineRepository, FlightRepository flightRepository, LocalParametersService localParametersService){
        super(airlineRepository);
        this.flightRepository = flightRepository;
        this.localParametersService = localParametersService;
    }

    public Airline addAirline(ABMAirlineForm airlineForm){
        Airline airline = new Airline();
        airline.setName(airlineForm.getAddAirlineName());
        airline.setShortName(airlineForm.getAddAirlineShortName());
        return createOne(airline);
    }

    public Airline editAirline(ABMAirlineForm airlineForm){
        Airline airline = repository.findById(airlineForm.getEditAirlineId()).orElseThrow(NoElementInDBException::new);
        airline.setName(airlineForm.getEditAirlineName());
        airline.setShortName(airlineForm.getEditAirlineShortName());
        return updateOne(airline);
    }

    public Airline deleteAirline(ABMAirlineForm airlineForm){
        Airline airline = repository.findById(airlineForm.getDeleteAirlineId()).orElseThrow(NoElementInDBException::new);
        repository.delete(airline);
        return airline;
    }

    public Flight findFligth(Long id){
        // esta mal esta excepcion
        return flightRepository.findById(id).orElseThrow(NoElementInDBException::new);
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
        LocalParameters localParameters = localParametersService.getLocalParameters();
        List<Flight> flights = this.flightRepository.findFlightsByDepartureDateGreaterThanEqualAndFromAndToAndGapMaxLessThanOrderByAirline(DateParser.parse(departureDate), from, to, localParameters.getGapMax()).orElse(new ArrayList<>());
        flights.forEach(f -> f.setSeats(f.getSeats().stream().filter(s -> s.getSeatClass().equals(seatClass)).collect(Collectors.toList())));
        return flights;

    }

    public Flight getFlightBySeatClass(Long id,  String seatClass) {
        Flight flight = this.findFligth(id);
        flight.setSeats(flight.getSeats().stream().filter(s -> SeatClass.valueOf(seatClass).equals(s.getSeatClass())).collect(Collectors.toList()));
        return flight;
    }

    public Flight addFlight(ABMFlightForm flightForm) {
        Flight flight = new Flight();
        flight.setAirline(repository.findById(flightForm.getAddAirlineId()).orElseThrow(NoElementInDBException::new));
        flight.setFrom(flightForm.getAddFlightFrom());
        flight.setTo(flightForm.getAddFlightTo());
        flight.setDepartureDate(flightForm.getAddFlightDepartureDate().toLocalDate());
        flight.setDepartureTime(flightForm.getAddFlightDepartureDate());
        flight.setArrivalDate(flightForm.getAddFlightArrivalDate().toLocalDate());
        flight.setArrivalTime(flightForm.getAddFlightArrivalDate());
        flight.setPrice(flightForm.getAddFlightPrice());
        flight.setEconomicCapacity(flightForm.getAddEconomic());
        flight.setBusinessCapacity(flightForm.getAddBusiness());
        flight.setFirstCapacity(flightForm.getAddFirst());
        List<FlightSeat> seats = buildSeats(flightForm);
        flight.setSeats(seats);
        return createFligth(flight);
    }

    private List<FlightSeat> buildSeats(ABMFlightForm flightForm) {
        List<FlightSeat> seats = new ArrayList<>();
        for (int i = 0; i < flightForm.getAddBusiness(); i++) {
            FlightSeat seat = new FlightSeat();
            seat.setPriceClass(flightForm.getAddFlightPrice());
            seat.setSeatClass(SeatClass.BUSINESS);
            seats.add(seat);
        }
        for (int i = 0; i < flightForm.getAddEconomic(); i++) {
            FlightSeat seat = new FlightSeat();
            seat.setPriceClass(flightForm.getAddFlightPrice());
            seat.setSeatClass(SeatClass.ECONOMIC);
            seats.add(seat);
        }
        for (int i = 0; i < flightForm.getAddFirst(); i++) {
            FlightSeat seat = new FlightSeat();
            seat.setPriceClass(flightForm.getAddFlightPrice());
            seat.setSeatClass(SeatClass.FIRST);
            seats.add(seat);
        }
        return seats;
    }
}
