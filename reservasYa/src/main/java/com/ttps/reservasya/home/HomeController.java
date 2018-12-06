package com.ttps.reservasya.home;

import com.ttps.reservasya.agencies.Agency;
import com.ttps.reservasya.airlines.Airline;
import com.ttps.reservasya.hotels.Hotel;
import com.ttps.reservasya.agencies.AgencyService;
import com.ttps.reservasya.airlines.AirlineService;
import com.ttps.reservasya.hotels.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @ModelAttribute(value = "module")
    String module() {
        return "home";
    }

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AgencyService agencyService;

    @ModelAttribute("hotels")
    public List<Hotel> populateHotels() {
        return hotelService.findAll();
    }

    @ModelAttribute("agencies")
    public List<Agency> populateAgency(){
        return agencyService.findAll();
    }

    @ModelAttribute("airlines")
    public List<Airline> populateAirline(){
        return airlineService.findAll();
    }


    @GetMapping(value = "/")
    String index(Principal principal) {
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

}
