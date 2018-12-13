package com.ttps.reservasya.controllers.searchbox;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/index")
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

    @PostMapping(value = "/searchFly")
    public String search(@Valid @ModelAttribute SearchFlyForm searchFlyForm, Errors errors, RedirectAttributes ra){
        if (errors.hasErrors()) {
            return "/index";
        }
        airlineService.findOneWayFlights(searchFlyForm.getDepartureDate(), searchFlyForm.getFrom(), searchFlyForm.getTo());
        return "lala";
    }



}
