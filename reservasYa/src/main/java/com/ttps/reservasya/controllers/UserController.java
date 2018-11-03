package com.ttps.reservasya.controllers;

import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.dto.UserDTO;
import com.ttps.reservasya.services.SecurityService;
import com.ttps.reservasya.services.UserService;
import com.ttps.reservasya.transformers.UserTransformer;
import com.ttps.reservasya.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {



    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    @ResponseBody
    public List<UserDTO> getUsers(){
        return this.userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/users/email/{email}")
    @ResponseBody
    public UserDTO getUserByEmail(@PathVariable("email") @Email String email) {
        return new UserDTO(this.userService.findByEmail(email));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El usuario no se encontro en el sistema")
    @ExceptionHandler(UserNotFoundException.class)
    public void userExceptionHandler(){
        /**
         * sdfgsdfgsdfgsdfgsdfg
         * sdfg
         * sdf
         * gsd
         * fg
         */
    }

    @GetMapping(value = "/users/userName/{userName}")
    @ResponseBody
    public UserDTO getUserByUserName(@PathVariable("userName") String userName) {
        return new UserDTO(this.userService.findByUserName(userName));
    }

    @PutMapping(value = "/user/add/")
    public void addUser(@RequestBody @Valid final UserDTO userDTO){
        this.userService.createUser(UserTransformer.toUser(userDTO));
    }
}
