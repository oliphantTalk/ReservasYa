package com.ttps.reservasya.controllers.airline;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.airlines.AirlineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping(value = "/search/fly")
@Controller
public class AirlineController {

        private  AirlineService airlineService;

        private  LocalParametersService localParametersService;

    @Autowired
    public AirlineController(AirlineService airlineService, LocalParametersService localParametersService) {
        this.airlineService = airlineService;
        this.localParametersService = localParametersService;
    }

    @GetMapping(value = "/{id}/{seatClass}/{passengers}")
        public String flyDetails(
                @Valid @PathVariable("id") Long id,
                @PathVariable("seatClass") String seatClass,
                @PathVariable("passengers") int passengers,
                @RequestParam(value = "return_id", required = false) String returnId, Model model) {
            LocalParameters localParameters = localParametersService.getLocalParameters();
            Flight flight = airlineService.getFlightBySeatClass(id, seatClass);
            model.addAttribute("flight", flight);
            model.addAttribute("seatClass", seatClass);
            model.addAttribute("passengers", passengers);
            if(!StringUtils.isBlank(returnId)){
                Flight flightReturn = airlineService.getFlightBySeatClass(Long.valueOf(returnId), seatClass);
                model.addAttribute("flightReturn", flightReturn);
            }
            double precioClase = 1;
            if (seatClass.equalsIgnoreCase("BUSINESS")) {
                precioClase = 1 + localParameters.getBusinessClassRate();
            }
            if (seatClass.equalsIgnoreCase("FIRST")) {
                precioClase = 1 + localParameters.getFirstClassRate();
            }
            model.addAttribute("precioClase", precioClase);

            return "/details/details";
        }




}


