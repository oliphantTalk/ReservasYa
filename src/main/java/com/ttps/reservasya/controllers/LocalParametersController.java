package com.ttps.reservasya.controllers;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.repository.LocalParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class LocalParametersController {

    private final LocalParametersRepository localParametersRepository;

    @Autowired
    public LocalParametersController(LocalParametersRepository localParametersRepository) {
        this.localParametersRepository = localParametersRepository;
    }

    @GetMapping(value = "/localParameters")
    public String getParameters(){
        return "thymeleaf/localParameters";
    }

    @ModelAttribute("parameters")
    public List<LocalParameters> populateParameters() {
        return this.localParametersRepository.findAll();
    }

}
