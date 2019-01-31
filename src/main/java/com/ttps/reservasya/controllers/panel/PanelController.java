package com.ttps.reservasya.controllers.panel;

import com.ttps.reservasya.config.DataLoader;
import com.ttps.reservasya.controllers.panel.form.*;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.settings.UserSettings;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.hotel.HotelService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Autowired
    private DataLoader dataLoader;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/resetDb")
    public void resetDataBase() throws Exception{
        dataLoader.run(new DefaultApplicationArguments(new String[]{"Algo", "Otro"}));
    }

    @GetMapping(value = "/admin")
    public String showAdminPanel(Model model){
        LocalParameters localParameters = localParametersService.getLocalParameters();
        List<User> users = userService.findAll();
        model.addAttribute("abmUserForm", new ABMUserForm());
        model.addAttribute("abmAirlineForm", new ABMAirlineForm());
        model.addAttribute("abmAgencyForm", new ABMAgencyForm());
        model.addAttribute("abmHotelForm", new ABMHotelForm());
        model.addAttribute("abmFlightForm", new ABMFlightForm());
        model.addAttribute("abmCarForm", new ABMCarForm());
        model.addAttribute("abmRoomForm", new ABMRoomForm());
        model.addAttribute("airlines", airlineService.findAll());
        model.addAttribute("agencies", agencyService.findAll());
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("users", users);
        model.addAttribute("params", localParameters);
        model.addAttribute("localParamsForm", new LocalParamsForm(localParameters));
        return "panel/admin";
    }

    @GetMapping(value = "/user")
    public String showUserPanel(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        UserSettings settings = userService.getUserSettingsByUserName(principal.getName());
        model.addAttribute("profileForm", new ProfileForm());
        model.addAttribute("settings", settings);
        model.addAttribute("user", user);
        return "panel/user";
    }


    @PostMapping(value = "/admin/localParams", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public LocalParameters updateLocalParams(Model model, @RequestBody LocalParamsForm localParamsForm, BindingResult result){
        return localParametersService.saveLocalParameters(localParamsForm);
    }

}
