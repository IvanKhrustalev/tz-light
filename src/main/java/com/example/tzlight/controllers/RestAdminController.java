package com.example.tzlight.controllers;

import com.example.tzlight.entity.Contract;
import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;
import com.example.tzlight.reporitory.ContractRepository;
import com.example.tzlight.service.adstract.AdminService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Контроллер администратора мероприятия", description = "Позволяет подписать договор при его наличии, " +
        "и создать мероприятие на основании его")
public class RestAdminController {
    private final AdminService adminService;
    private final ContractRepository contractRepository;

    public RestAdminController(AdminService adminService, ContractRepository contractRepository) {
        this.adminService = adminService;
        this.contractRepository = contractRepository;
    }
    @PostMapping
    @Tag(name = "Подписание договора")
    @ApiResponses(value = {
            @ApiResponse(description = "Успешно подписан", responseCode = "200"),
            @ApiResponse(description = "Произошла ошибка", responseCode = "400")
    })
    public ResponseEntity signContract(Principal principal, @RequestBody @Parameter(name = "Договор") Contract contract) {
        try {
            int contractNumber = contract.getNumber();
            adminService.agreementContract(contractNumber, principal.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @PostMapping("{contractId}")
    @Tag(name = "Создать мероприятие")
    @ApiResponses(value = {
            @ApiResponse(description = "мероприятие создано", responseCode = "200"),
            @ApiResponse(description = "Произошла ошибка", responseCode = "400")
    })
    public ResponseEntity<Event> createNewEvent(Principal principal, @PathVariable @Parameter(name = "Номер договора") Long contractId,
                                                @RequestBody @Parameter(name = "Мероприятие") Event event) {
        User adminByUserName = adminService.findByUserName(principal.getName());
        Event createdEvent = adminService.createEvent(contractId, adminByUserName, event);
        return new ResponseEntity<>(createdEvent, HttpStatusCode.valueOf(200));
    }
}
