package com.ttps.reservasya.controllers.airline;

import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
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

        @Autowired
        private AirlineService airlineService;

        @GetMapping(value = "/{id}/{seatClass}")
        public String flyDetails(@Valid @PathVariable("id") Long id, @PathVariable("seatClass") String seatClass, @RequestParam(value = "return_id", required = false) String returnId, Model model){
            Flight flight = airlineService.getFlightBySeatClass(id, seatClass);
            model.addAttribute("flight", flight);
            model.addAttribute("seatClass", seatClass);
            if(!StringUtils.isBlank(returnId)){
                Flight flightReturn = airlineService.getFlightBySeatClass(Long.valueOf(returnId), seatClass);
                model.addAttribute("flightReturn", flightReturn);
            }
            return "/details/details";
        }




}


