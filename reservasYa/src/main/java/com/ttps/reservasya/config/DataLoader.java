package com.ttps.reservasya.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessentity.Agency;
import com.ttps.reservasya.models.businessentity.Airline;
import com.ttps.reservasya.models.businessentity.Hotel;
import com.ttps.reservasya.models.businessitem.Car;
import com.ttps.reservasya.models.businessitem.Flight;
import com.ttps.reservasya.models.businessitem.Room;
import com.ttps.reservasya.models.transaction.StateTransaction;
import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.user.Role;
import com.ttps.reservasya.user.User;
import com.ttps.reservasya.repository.LocalParametersRepository;
import com.ttps.reservasya.services.modelcrud.*;
import com.ttps.reservasya.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final AirlineService airlineService;
    private final HotelService hotelService;
    private final AgencyService agencyService;
    private final TransactionService transactionService;
    private final ObjectMapper jsonMapper;
    private final LocalParametersRepository localParametersRepository;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService, AirlineService airlineService, HotelService hotelService, AgencyService agencyService, TransactionService transactionService, ObjectMapper objectMapper, LocalParametersRepository localParametersRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.airlineService = airlineService;
        this.hotelService = hotelService;
        this.agencyService = agencyService;
        this.transactionService = transactionService;
        this.jsonMapper = objectMapper;
        this.localParametersRepository = localParametersRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = jsonMapper.readValue(new URL("file:src/test/resources/users_h2.json"), new TypeReference<List<User>>() {
        });
        List<Role> roles = jsonMapper.readValue(new URL("file:src/test/resources/roles_h2.json"), new TypeReference<List<Role>>() {
        });
        List<Hotel> hotels = jsonMapper.readValue(new URL("file:src/test/resources/hotels_h2.json"), new TypeReference<List<Hotel>>() {
        });
        List<Room> rooms = jsonMapper.readValue(new URL("file:src/test/resources/rooms_h2.json"), new TypeReference<List<Room>>() {
        });
        List<Airline> airlines = jsonMapper.readValue(new URL("file:src/test/resources/airlines_h2.json"), new TypeReference<List<Airline>>() {
        });
        List<Flight> flights = jsonMapper.readValue(new URL("file:src/test/resources/flights_h2.json"), new TypeReference<List<Flight>>() {
        });
        List<Agency> agencies = jsonMapper.readValue(new URL("file:src/test/resources/agencies_h2.json"), new TypeReference<List<Agency>>() {
        });
        List<Car> cars = jsonMapper.readValue(new URL("file:src/test/resources/cars_h2.json"), new TypeReference<List<Car>>() {
        });
        List<StateTransaction> stateTransactions = jsonMapper.readValue(new URL("file:src/test/resources/transactionStates_h2.json"), new TypeReference<List<StateTransaction>>() {
        });

        localParametersRepository.save(new LocalParameters());
        roleService.createAll(roles);
        users.forEach(userService::createOne);
        agencyService.createAll(agencies);
        agencyService.createCars(cars);
        airlineService.createAll(airlines);
        airlineService.createFlights(flights);
        hotelService.createAll(hotels);
        hotelService.createRooms(rooms);
        transactionService.createStates(stateTransactions);
        Transaction transaction = new Transaction();
        transaction.getItems().addAll(Arrays.asList(cars.get(0), flights.get(0), rooms.get(0)));
        transactionService.createOne(transaction);
        transactionService.updateOne(transaction);

    }

}
