package com.ttps.reservasya.controllers.airline;

import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.models.businessitem.airline.flights.SeatClass;
import com.ttps.reservasya.services.airlines.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RequestMapping(value = "/search/fly")
@Controller
public class AirlineController {

        @Autowired
        private AirlineService airlineService;

        @GetMapping(value = "/{id}/{seatClass}")
        public String flyDetails(@Valid @PathVariable("id") long id, @PathVariable("seatClass") String seatClass, Model model){
            Flight flight = airlineService.findFligth(id).get();
            flight.setSeats(flight.getSeats().stream().filter(s -> SeatClass.valueOf(seatClass).equals(s.getSeatClass())).collect(Collectors.toList()));
            model.addAttribute("flight", flight);
            return "/details/details";
        }


}


