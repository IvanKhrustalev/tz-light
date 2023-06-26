package com.example.tzlight.controllers;

import com.example.tzlight.entity.User;
import com.example.tzlight.service.adstract.RegistrationUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@Tag(name = "Контроллер регистрации", description = "Регистрация пользователей по email и password")
public class RegistrationController {
    private final RegistrationUserService registrationUserService;

    public RegistrationController(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }
    @PostMapping
    @Tag(name = "Зарегистрироваться")
    @ApiResponses(value = {
            @ApiResponse(description = "Регистрация прошла успешно", responseCode = "200"),
            @ApiResponse(description = "Произошла ошибка", responseCode = "400")
    })
    public ResponseEntity addNewUser(@RequestBody @Parameter(name = "Пользователь, запрашивающий регистрацию") User user) {
        boolean register = registrationUserService.register(user);
        return new ResponseEntity<>(register, HttpStatusCode.valueOf(200));
    }
}
