package com.ttps.reservasya.controllers.hotel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttps.reservasya.controllers.panel.form.ABMRoomForm;
import com.ttps.reservasya.controllers.panel.form.ABMHotelForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.models.businessitem.hotel.Room;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.hotel.HotelService;
import com.ttps.reservasya.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    @PostMapping(value = "/hotel/room/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Room addRoom(@RequestBody ABMRoomForm roomForm, ModelMap model) {
        return hotelService.addRoom(roomForm);
    }

    @PostMapping(value = "/hotel/rooms", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getHotelById(@RequestBody Map<String, Long> getHotelId, ModelMap model) throws JsonProcessingException {
        Hotel hotel = hotelService.findById(getHotelId.get("getHotelId")).orElseThrow(NoElementInDBException::new);
        List<Room> rooms = hotel.getRooms();
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", rooms);
        return CustomObjectMapper.getMapper().writeValueAsString(rooms);
    }

    @PostMapping(value = "/hotel/room/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String removeRoom(@RequestBody Map<String, Long> deleteRoomId, ModelMap model) throws JsonProcessingException {
        Room room = hotelService.removeRoom(deleteRoomId.get("deleteRoomId"));
        return CustomObjectMapper.getMapper().writeValueAsString(room);
    }

}
