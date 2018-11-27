package com.ttps.reservasya.controllers;

import com.ttps.reservasya.models.businessentity.Agency;
import com.ttps.reservasya.models.businessentity.Airline;
import com.ttps.reservasya.models.businessentity.Hotel;
import com.ttps.reservasya.services.modelcrud.AgencyService;
import com.ttps.reservasya.services.modelcrud.AirlineService;
import com.ttps.reservasya.services.modelcrud.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

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


}
