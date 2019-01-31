package com.ttps.reservasya.controllers.searchbox;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RequestMapping(value = "/search")
@Controller
public class SearchController {

    private AirlineService airlineService;

    private HotelService hotelService;

    private AgencyService agencyService;

    private LocalParametersService localParametersService;

    @Autowired
    public SearchController(AirlineService airlineService, HotelService hotelService, AgencyService agencyService, LocalParametersService localParametersService) {
        this.airlineService = airlineService;
        this.hotelService = hotelService;
        this.agencyService = agencyService;
        this.localParametersService = localParametersService;
    }

    @PostMapping(value = "/fly")
    public String searchFly(@NotEmpty @Valid @ModelAttribute SearchFlyForm searchFlyForm, Errors errors, RedirectAttributes ra, Model model){
        LocalParameters localParameters = localParametersService.getLocalParameters();
        if (errors.hasErrors()) {
            return "/";
        }
        model.addAttribute("flights", airlineService.findOneWayFlights(searchFlyForm.getDepartureDate(), searchFlyForm.getFrom(), searchFlyForm.getTo(), SeatClass.valueOf(searchFlyForm.getClase())));
        if(!StringUtils.isBlank(searchFlyForm.getReturnDate())) {
            List<Flight> flightsReturn = airlineService.findOneWayFlights(searchFlyForm.getReturnDate(), searchFlyForm.getTo(), searchFlyForm.getFrom(), SeatClass.valueOf(searchFlyForm.getClase()));
            if(!flightsReturn.isEmpty()){
            model.addAttribute("flightsReturn", flightsReturn);}
        }
        double precioClase = 1;
        if (searchFlyForm.getClase().equalsIgnoreCase("BUSINESS")) {
            precioClase = 1 + localParameters.getBusinessClassRate();
        }
        if (searchFlyForm.getClase().equalsIgnoreCase("FIRST")) {
            precioClase = 1 + localParameters.getFirstClassRate();
        }
        model.addAttribute("precioClase", precioClase);
        model.addAttribute("form", searchFlyForm);
        model.addAttribute("seatClass", searchFlyForm.getClase());
        model.addAttribute("passengers", searchFlyForm.getFlyPassenger());
        return "/result/result";
    }

    @PostMapping(value = "/hotel")
    public String searchHotel(@Valid @ModelAttribute SearchHotelForm searchHotelForm, Errors errors, RedirectAttributes ra, Model model){
        if (errors.hasErrors()) {
            return "/";
        }
        //model.addAttribute("hotels", hotelService.searchHotelForDestination(searchHotelForm.getHotelTo(), searchHotelForm.getHotelPassenger()));
        model.addAttribute("hotels", hotelService.findAll());
        return "/result/result";
    }

    @PostMapping(value = "/car")
    public String searchCar(@Valid @ModelAttribute SearchCarForm searchCarForm, Errors errors, RedirectAttributes ra, Model model){
        if (errors.hasErrors()) {
            return "/";
        }
//        model.addAttribute("cars", agencyService.searchCarForDestination(searchCarForm.getPickup(), searchCarForm.getCarPassenger()));
        model.addAttribute("cars", agencyService.findAll());
        return "/result/result";
    }




}
