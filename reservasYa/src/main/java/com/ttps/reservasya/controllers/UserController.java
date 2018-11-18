package com.ttps.reservasya.controllers;

import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.users.dto.UserDTO;
import com.ttps.reservasya.services.modelcrud.UserService;
import com.ttps.reservasya.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public UserDTO getUser(@PathVariable("id") Long id) {
        return new UserDTO(this.userService.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<UserDTO> getUsers(){
        return this.userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/users/email/{email}")
    @ResponseBody
    public UserDTO getUserByEmail(@PathVariable("email") @Email String email) {
        return new UserDTO(this.userService.findByEmail(email).orElseThrow(UserNotFoundException::new));
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

    @PutMapping(value = "/user")
    public void addUser(@RequestBody @Valid final UserDTO userDTO){
        this.userService.createOne(UserTransformer.toUser(userDTO));
    }
}
