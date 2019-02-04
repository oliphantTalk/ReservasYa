package com.ttps.reservasya.controllers.agency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttps.reservasya.controllers.panel.form.ABMAgencyForm;
import com.ttps.reservasya.controllers.panel.form.ABMCarForm;
import com.ttps.reservasya.controllers.searchbox.SearchCarForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import com.ttps.reservasya.models.businessitem.airline.flights.Flight;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.utils.CustomObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Agency createAgency(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.addAgency(agencyForm);

    }

    @PostMapping(value = "/agency/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Agency editAgency(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.editAgency(agencyForm);
    }

    @PostMapping(value = "/agency/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Agency deleteAgency(Model model, @RequestBody ABMAgencyForm agencyForm, BindingResult result){
        return agencyService.deleteAgency(agencyForm);
    }

    @PostMapping(value = "/agency/car/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Car addCar(@RequestBody ABMCarForm carForm, ModelMap model) {
        return agencyService.addCar(carForm);
    }

    @PostMapping(value = "/agency/cars", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getAgencyById(@RequestBody Map<String, Long> getAgencyId, ModelMap model) throws JsonProcessingException {
        Agency agency = agencyService.findById(getAgencyId.get("getAgencyId")).orElseThrow(NoElementInDBException::new);
        List<Car> cars = agency.getCars();
        model.addAttribute("agency", agency);
        model.addAttribute("cars", cars);
        return CustomObjectMapper.getMapper().writeValueAsString(cars);
    }

    @PostMapping(value = "/agency/car/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String removeCar(@RequestBody Map<String, Long> deleteCarId, ModelMap model) throws JsonProcessingException {
        Car car = agencyService.removeCar(deleteCarId.get("deleteCarId"));
        return CustomObjectMapper.getMapper().writeValueAsString(car);
    }


    @GetMapping(value = "/search/car/{id}/details")
    public String flyDetails(
            @Valid @PathVariable("id") Long carId,
            @RequestParam("from") String from,
            @RequestParam("date_from") String dateFrom,
            @RequestParam("to") String to,
            @RequestParam("date_to") String dateTo,
            @RequestParam("rent_days") int rentDays,
            Model model) {
        SearchCarForm carForm = new SearchCarForm();
        LocalParameters localParameters = localParametersService.getLocalParameters();
        carForm.setPickup(from);
        carForm.setRetrieve(to);
        carForm.setDateFrom(dateFrom);
        carForm.setDateTo(dateTo);
        Optional<Car> car = agencyService.findCar(carId);
        model.addAttribute("car", car.orElseThrow(NoElementInDBException::new));
        model.addAttribute("carForm", carForm);
        model.addAttribute("rentDays", rentDays);
        return "/details/details";
    }

}
