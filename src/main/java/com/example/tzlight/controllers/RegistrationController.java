package com.example.tzlight.controllers;

import com.example.tzlight.entity.User;
import com.example.tzlight.service.adstract.RegistrationUserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationUserService registrationUserService;

    public RegistrationController(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }
    @PostMapping
    public ResponseEntity addNewUser(@RequestBody User user) {
        boolean register = registrationUserService.register(user);
        return new ResponseEntity<>(register, HttpStatusCode.valueOf(200));
    }
}
