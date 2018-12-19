package com.ttps.reservasya.controllers.checkout;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.repository.transaction.UserTransactionHistoryRepository;
import com.ttps.reservasya.services.airlines.AirlineService;
import com.ttps.reservasya.services.checkout.CheckoutService;
import com.ttps.reservasya.services.transaction.TransactionService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private UserTransactionHistoryRepository historyRepository;

    @GetMapping(value = "/fly")
    public String createTransactionForOneFly(Model model, Principal principal,
                                             @RequestParam("flight_id") Long flightId,
                                             @RequestParam("passengers") int passengers,
                                             @RequestParam("price") double price,
                                             @RequestParam(value = "flight_return_id", required = false) Long flightReturnId ){
        List<BusinessItem> items = findFlights(flightId, flightReturnId);
        items.forEach(i -> i.setPrice(price));
        checkoutService.startTransaction(principal.getName(), items, passengers);
        model.addAttribute(new CheckoutForm() );
        return "checkout/checkout";
    }

    @PostMapping("/fly")
    public String postCheckoutForm(@Valid @ModelAttribute CheckoutForm form, Errors errors, RedirectAttributes ra, Model model, Principal principal){
        if(errors.hasErrors()){
            return "/";
        }
        checkoutService.approveTransaction(principal.getName(), form.getPointsToConvert(), form);
        return "thanks/thanks";
    }

    private List<BusinessItem> findFlights(Long flightId, Long flightReturnId) {
        List<BusinessItem> items = new ArrayList<>();
        items.add(airlineService.findFligth(flightId));
        if(flightReturnId != null){
            items.add(airlineService.findFligth(flightReturnId));
        }
        return items;
    }



}
