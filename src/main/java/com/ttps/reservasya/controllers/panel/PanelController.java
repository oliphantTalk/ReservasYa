package com.ttps.reservasya.controllers.panel;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.services.LocalParametersService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nahuelbarrena on 05/01/19
 */
@RequestMapping(value = "/panel")
@Controller
public class PanelController {


    @Autowired
    private LocalParametersService localParametersService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String showAdminPanel(Model model){
        LocalParameters localParameters = localParametersService.getLocalParameters();
        List<User> users = userService.findAll();
        model.addAttribute("addUserForm", new ABMUserForm());
        model.addAttribute("users", users);
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
    public LocalParameters updateLocalParams(Model model, /*@ModelAttribute LocalParamsForm localParamsForm*/ @RequestBody LocalParamsForm localParamsForm, BindingResult result){

        return localParametersService.saveLocalParameters(localParamsForm);

    }

    @PostMapping(value = "/admin/user/add", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User createUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.addUser(userForm);
    }

    @PostMapping(value = "/admin/user/edit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User editUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.editUser(userForm);
    }

    @PostMapping(value = "/admin/user/delete", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User deleteUser(Model model, @RequestBody ABMUserForm userForm, BindingResult result){
        return userService.deleteUser(userForm);
    }

}
