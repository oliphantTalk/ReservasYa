package com.ttps.reservasya.controllers.checkout;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.models.user.settings.UserSettings;
import com.ttps.reservasya.repository.transaction.UserTransactionHistoryRepository;
import com.ttps.reservasya.services.agencies.AgencyService;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.checkout.CheckoutService;
import com.ttps.reservasya.services.hotel.HotelService;
import com.ttps.reservasya.services.transaction.TransactionService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    private TransactionService transactionService;

    private AirlineService airlineService;

    private UserTransactionHistoryRepository historyRepository;

    private UserService userService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, TransactionService transactionService, AirlineService airlineService, UserTransactionHistoryRepository historyRepository, UserService userService) {
        this.checkoutService = checkoutService;
        this.transactionService = transactionService;
        this.airlineService = airlineService;
        this.historyRepository = historyRepository;
        this.userService = userService;
    }


    @PostMapping("/confirm")
    public String postCheckoutForm(@Valid @ModelAttribute CheckoutForm form, Errors errors, RedirectAttributes ra, Model model, Principal principal, HttpSession session){
        if(errors.hasErrors()){
            return "/";
        }
        UserSettings userSettings = userService.getUserSettingsByUserName(principal.getName());
        int pointsBefore = userSettings.getPointsToUse();
        model.addAttribute("pointsBefore", pointsBefore);
        model.addAttribute("pointsConverted", form.getPointsToConvert());
        checkoutService.approveTransaction(principal.getName(), form.getPointsToConvert(), form);
        model.addAttribute("newPoints", userSettings.getPointsToUse());
        model.addAttribute("earnedPoints", form.getPointsToConvert() + userSettings.getPointsToUse() - pointsBefore);
        model.addAttribute("lastTransaction", transactionService.findById(form.getTId()).get());
        session.invalidate();
        return "thanks/thanks";
    }

    @GetMapping(value = "/fly")
    public String createTransactionForOneFly(Model model, Principal principal,
                                             @RequestParam("flight_id") Long flightId,
                                             @RequestParam("passengers") int passengers,
                                             @RequestParam("price") double price,
                                             @RequestParam(value = "flight_return_id", required = false) Long flightReturnId ){
        List<BusinessItem> items = findFlights(flightId, flightReturnId);
        items.forEach(i -> i.setPrice(price));
       toCheckout(model, principal, items, passengers);
        return "checkout/checkout";
    }


    @GetMapping(value = "/car")
    public String createTransactionForCar(Model model, Principal principal,
                                             @RequestParam("car_id") Long carId,
                                             @RequestParam("rentDays") int rentDays,
                                             @RequestParam("price") double price)
                                             {
        List<BusinessItem> items = findCars(carId);
        items.forEach(i -> i.setPrice(price));
        toCheckout(model, principal, items, 1);
        return "checkout/checkout";
    }



    @GetMapping(value = "/room")
    public String createTransactionForRoom(Model model, Principal principal,
                                          @RequestParam("room_id") Long roomId,
                                          @RequestParam("rentDays") int rentDays,
                                          @RequestParam("price") double price)
    {
        List<BusinessItem> items = findRooms(roomId);
        items.forEach(i -> i.setPrice(price));
        toCheckout(model, principal, items, 1);
        return "checkout/checkout";
    }

    private void toCheckout(Model model, Principal principal, List<BusinessItem> items, int passengers) {
        Transaction transaction = checkoutService.startTransaction(principal.getName(), items, passengers);
        model.addAttribute(new CheckoutForm(transaction.getId()) );
        int userAvailabalePoints = userService.getUserSettingsByUserName(principal.getName()).getPointsToUse();
        model.addAttribute("availablePoints", userAvailabalePoints);
    }

    private List<BusinessItem> findFlights(Long flightId, Long flightReturnId) {
        List<BusinessItem> items = new ArrayList<>();
        items.add(airlineService.findFligth(flightId));
        if(flightReturnId != null){
            items.add(airlineService.findFligth(flightReturnId));
        }
        return items;
    }

    private List<BusinessItem> findCars(Long carId){
        List<BusinessItem> items = new ArrayList<>();
        items.add(agencyService.findCar(carId));
        return items;
    }

    private List<BusinessItem> findRooms(Long roomId){
        List<BusinessItem> items = new ArrayList<>();
        items.add(hotelService.findRoom(roomId));
        return items;
    }



}
