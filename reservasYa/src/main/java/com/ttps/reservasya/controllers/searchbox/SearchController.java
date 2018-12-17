package com.ttps.reservasya.controllers.searchbox;

import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/search")
@Controller
public class SearchController {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AgencyService agencyService;

    @PostMapping(value = "/fly")
    public String searchFly(@Valid @ModelAttribute SearchFlyForm searchFlyForm, Errors errors, RedirectAttributes ra, Model model){
        if (errors.hasErrors()) {
            return "/";
        }
        model.addAttribute("flights", airlineService.findOneWayFlights(searchFlyForm.getDepartureDate(), searchFlyForm.getFrom().toLowerCase(), searchFlyForm.getTo().toLowerCase(), SeatClass.valueOf(searchFlyForm.getClase())));
        model.addAttribute("flightsReturn", airlineService.findOneWayFlights(searchFlyForm.getDepartureDate(), searchFlyForm.getFrom(), searchFlyForm.getTo(), SeatClass.valueOf(searchFlyForm.getClase())));
        model.addAttribute("seatClass", searchFlyForm.getClase());
        return "/result/result";
    }

    @PostMapping(value = "/hotel")
    public String searchHotel(@Valid @ModelAttribute SearchHotelForm searchHotelForm, Errors errors, RedirectAttributes ra, Model model){
        if (errors.hasErrors()) {
            return "/";
        }
        model.addAttribute("hotels", hotelService.searchHotelForDestination(searchHotelForm.getTo(), searchHotelForm.getPassenger()));
        return "/result/result";
    }

    @PostMapping(value = "/car")
    public String searchCar(@Valid @ModelAttribute SearchCarForm searchCarForm, Errors errors, RedirectAttributes ra, Model model){
        if (errors.hasErrors()) {
            return "/";
        }
        model.addAttribute("cars", agencyService.searchCarForDestination(searchCarForm.getPickup(), searchCarForm.getPassenger()));
        return "/result/result";
    }




}
