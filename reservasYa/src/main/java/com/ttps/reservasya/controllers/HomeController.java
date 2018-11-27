package com.ttps.reservasya.controllers;

import com.ttps.reservasya.models.users.dto.UserDTO;
import com.ttps.reservasya.services.nonmodel.SecurityService;
import com.ttps.reservasya.services.modelcrud.UserService;
import com.ttps.reservasya.transformers.UserTransformer;
import com.ttps.reservasya.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public HomeController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "jsp/registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "jsp/registration.jsp";
        }
        userService.createOne(UserTransformer.toUser(userForm));
        securityService.autologin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/welcome";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Nombre de usuario y password son invalidos");
        if (logout != null)
            model.addAttribute("message", "La sesion se cerro correctamente");
        return "jsp/login";

    }

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model) {
        return "jsp/welcome";
    }

}
