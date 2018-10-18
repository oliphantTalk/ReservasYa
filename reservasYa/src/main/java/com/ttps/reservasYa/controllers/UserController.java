package com.ttps.reservasYa.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasYa.models.User;
import com.ttps.reservasYa.models.dto.UserDTO;
import com.ttps.reservasYa.services.UserService;
import com.ttps.reservasYa.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
/**
 *     @RequestMapping(value = "/itinerary", method = RequestMethod.GET)
 *     @ResponseBody
 *     public ItemServiceDTO getItineraries(@RequestParam(value = "from_date") String departureDate,
 *                                          @RequestParam(value = "return_date", required = false) String returnDate,
 *                                          @RequestParam(value = "from") String from,
 *                                          @RequestParam(value = "to") String to,
 *                                          @RequestParam(value = "client_type") String clientType) {
 *         DateUtil.isNotBeforeDay(departureDate, returnDate);
 *         ClientTypeValidate.isClientValid(clientType);
 *         LOGGER.info(String.format("Los parametros son %s %s %s %s %s", departureDate, returnDate, from, to, clientType));
 *         return itineraryService.getItineraries(departureDate, returnDate, from, to, clientType).getItems().get(0);
 *
 *     }
 */
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getUsers(){
        return this.userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
