package com.ttps.reservasya.controllers.panel;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.services.LocalParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author nahuelbarrena on 05/01/19
 */
@RequestMapping(value = "/panel")
@Controller
public class PanelController {


    @Autowired
    private LocalParametersService localParametersService;

    @GetMapping(value = "/admin")
    public String showAdminPanel(Model model){
        LocalParameters localParameters = localParametersService.getLocalParameters();
        model.addAttribute("params", localParameters);
        model.addAttribute("localParamsForm", new LocalParamsForm(localParameters));
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


    @PostMapping(value = "/admin/localParams", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public LocalParameters localParams(Model model, /*@ModelAttribute LocalParamsForm localParamsForm*/ @RequestBody LocalParamsForm localParamsForm, BindingResult result){

        return localParametersService.saveLocalParameters(localParamsForm);

    }

}
