package com.ttps.reservasya.controllers.home;

import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
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
