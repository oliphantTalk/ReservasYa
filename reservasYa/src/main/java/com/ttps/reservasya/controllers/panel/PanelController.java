package com.ttps.reservasya.controllers.panel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nahuelbarrena on 05/01/19
 */
@RequestMapping(value = "/panel")
@Controller
public class PanelController {



    @GetMapping(value = "/admin")
    public String showAdminPanel(){
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


}
