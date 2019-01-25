package com.ttps.reservasya.controllers.hotel;

import com.ttps.reservasya.controllers.panel.form.ABMHotelForm;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotelController {

    private final HotelService hotelService;

    private final LocalParametersService localParametersService;

    @Autowired
    public HotelController(HotelService hotelService, LocalParametersService localParametersService) {
        this.hotelService = hotelService;
        this.localParametersService = localParametersService;
    }

    @PostMapping(value = "/hotel/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Hotel createhotel(Model model, @RequestBody ABMHotelForm hotelForm, BindingResult result){
        return hotelService.addHotel(hotelForm);

    }

    @PostMapping(value = "/hotel/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Hotel edithotel(Model model, @RequestBody ABMHotelForm hotelForm, BindingResult result){
        return hotelService.editHotel(hotelForm);
    }

    @PostMapping(value = "/hotel/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Hotel deletehotel(Model model, @RequestBody ABMHotelForm hotelForm, BindingResult result){
        return hotelService.deleteHotel(hotelForm);
    }

}
