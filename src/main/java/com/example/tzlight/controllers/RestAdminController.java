package com.example.tzlight.controllers;

import com.example.tzlight.entity.Contract;
import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;
import com.example.tzlight.reporitory.ContractRepository;
import com.example.tzlight.service.adstract.AdminService;
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
public class RestAdminController {
    private final AdminService adminService;
    private final ContractRepository contractRepository;

    public RestAdminController(AdminService adminService, ContractRepository contractRepository) {
        this.adminService = adminService;
        this.contractRepository = contractRepository;
    }
    @PostMapping
    public ResponseEntity signContract(Principal principal, @RequestBody Contract contract) {
        try {
            int contractNumber = contract.getNumber();
            adminService.agreementContract(contractNumber, principal.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @PostMapping("{contractId}")
    public ResponseEntity<Event> createNewEvent(Principal principal, @PathVariable Long contractId,
                                                @RequestBody Event event) {
        User adminByUserName = adminService.findByUserName(principal.getName());
        Event createdEvent = adminService.createEvent(contractId, adminByUserName, event);
        return new ResponseEntity<>(createdEvent, HttpStatusCode.valueOf(200));
    }
}
