package com.ttps.reservasya.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasya.models.businessentity.Agency;
import com.ttps.reservasya.models.businessentity.Airline;
import com.ttps.reservasya.models.businessentity.Hotel;
import com.ttps.reservasya.models.businessitem.Car;
import com.ttps.reservasya.models.businessitem.Flight;
import com.ttps.reservasya.models.businessitem.Room;
import com.ttps.reservasya.models.users.Role;
import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.services.modelcrud.*;
import com.ttps.reservasya.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final AirlineService airlineService;
    private final HotelService hotelService;
    private final AgencyService agencyService;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService, AirlineService airlineService, HotelService hotelService, AgencyService agencyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.airlineService = airlineService;
        this.hotelService = hotelService;
        this.agencyService = agencyService;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/users_h2.json"), new TypeReference<List<User>>() {
        });
        List<Role> roles = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/roles_h2.json"), new TypeReference<List<Role>>() {
        });
        List<Hotel> hotels = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/hotels_h2.json"), new TypeReference<List<Hotel>>() {
        });
        List<Room> rooms = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/rooms_h2.json"), new TypeReference<List<Room>>() {
        });
        List<Airline> airlines = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/airlines_h2.json"), new TypeReference<List<Airline>>() {
        });
        List<Flight> flights = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/flights_h2.json"), new TypeReference<List<Flight>>() {
        });
        List<Agency> agencies = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/agencies_h2.json"), new TypeReference<List<Agency>>() {
        });
        List<Car> cars = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/cars_h2.json"), new TypeReference<List<Car>>() {
        });

        roleService.createAll(roles);
        userService.createAll(users);
        airlineService.createFlights(flights);
        airlineService.createAll(airlines);
        agencyService.createCars(cars);
        agencyService.createAll(agencies);
        hotelService.createRooms(rooms);
        hotelService.createAll(hotels);


    }

}
