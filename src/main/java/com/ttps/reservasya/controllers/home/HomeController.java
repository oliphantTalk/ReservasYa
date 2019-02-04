package com.ttps.reservasya.controllers.home;

import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {


    private HotelService hotelService;

    private AgencyService agencyService;

    private AirlineService airlineService;

    @Autowired
    public HomeController(HotelService hotelService, AgencyService agencyService, AirlineService airlineService) {
        this.hotelService = hotelService;
        this.agencyService = agencyService;
        this.airlineService = airlineService;
    }

    @ModelAttribute(value = "module")
    public String module() {
        return "home";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("sHotels", hotelService.findAll());
        model.addAttribute("sFlights", airlineService.findFligths());
        model.addAttribute("sAgency", agencyService.findAll());
        return "home/home";
    }

}
