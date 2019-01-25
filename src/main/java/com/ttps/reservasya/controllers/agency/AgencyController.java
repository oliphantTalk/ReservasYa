package com.ttps.reservasya.controllers.agency;

import com.ttps.reservasya.controllers.panel.form.ABMAgencyForm;
import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.agencies.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgencyController {

    private final AgencyService agencyService;

    private final LocalParametersService localParametersService;

    @Autowired
    public AgencyController(AgencyService agencyService, LocalParametersService localParametersService) {
        this.agencyService = agencyService;
        this.localParametersService = localParametersService;
    }


    @PostMapping(value = "/agency/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Agency createAirline(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.addAgency(agencyForm);

    }

    @PostMapping(value = "/agency/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Agency editAirline(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.editAgency(agencyForm);
    }

    @PostMapping(value = "/agency/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Agency deleteAirline(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.deleteAgency(agencyForm);
    }

}
