package com.ttps.reservasya.controllers.panel;

import com.ttps.reservasya.controllers.panel.form.*;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.airline.Airline;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nahuelbarrena on 05/01/19
 */
@RequestMapping(value = "/panel")
@Controller
public class PanelController {


    @Autowired
    private LocalParametersService localParametersService;

    @Autowired
    private UserService userService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/admin")
    public String showAdminPanel(Model model){
        LocalParameters localParameters = localParametersService.getLocalParameters();
        List<User> users = userService.findAll();
        model.addAttribute("abmUserForm", new ABMUserForm());
        model.addAttribute("abmAirlineForm", new ABMAirlineForm());
        model.addAttribute("abmAgencyForm", new ABMAgencyForm());
        model.addAttribute("abmHotelForm", new ABMHotelForm());
        model.addAttribute("airlines", airlineService.findAll());
        model.addAttribute("agencies", agencyService.findAll());
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("users", users);
        model.addAttribute("params", localParameters);
        model.addAttribute("localParamsForm", new LocalParamsForm(localParameters));
        return "panel/admin";
    }

    @GetMapping(value = "/user")
    public String showUserPanel(){
        return "panel/user";
    }

    @GetMapping(value = "/comercial")
    public String showComercialPanel(){
        return "panel/comercial";
    }


    @PostMapping(value = "/admin/localParams", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public LocalParameters updateLocalParams(Model model, /*@ModelAttribute LocalParamsForm localParamsForm*/ @RequestBody LocalParamsForm localParamsForm, BindingResult result){

        return localParametersService.saveLocalParameters(localParamsForm);

    }
    /////////////pasasr al user controller


}
