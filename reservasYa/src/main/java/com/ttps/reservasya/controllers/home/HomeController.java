package com.ttps.reservasya.controllers.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @ModelAttribute(value = "module")
    public String module() {
        return "home";
    }

    @GetMapping(value = "/")
    public String index() {
        return "home/home";
    }

}
