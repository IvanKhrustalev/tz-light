package com.example.tzlight.controllers;

import com.example.tzlight.entity.Event;
import com.example.tzlight.reporitory.EventRepository;
import com.example.tzlight.service.adstract.ParticipantService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/participant/")
public class ParticipantRestController {
    private final ParticipantService participantService;
    public ParticipantRestController(ParticipantService participantService) {
        this.participantService = participantService;
    }
    @PostMapping("{participantNames}")
    public ResponseEntity<Boolean> signInEvent(@PathVariable String participantNames,
                                               @RequestBody Event event) {
        boolean b = participantService.SignUpForEvent(event, participantNames);
        return new ResponseEntity<>(b, HttpStatusCode.valueOf(200));
    }
}
