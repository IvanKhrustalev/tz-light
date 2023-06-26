package com.example.tzlight.controllers;

import com.example.tzlight.entity.Event;
import com.example.tzlight.reporitory.EventRepository;
import com.example.tzlight.service.adstract.ParticipantService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Контроллер участника мероприятия")
public class ParticipantRestController {
    private final ParticipantService participantService;
    public ParticipantRestController(ParticipantService participantService) {
        this.participantService = participantService;
    }
    @PostMapping("{participantNames}")
    @Tag(name = "Принять участие в мероприятие")
    @ApiResponses(value = {
            @ApiResponse(description = "Успешное выполнение", responseCode = "200"),
            @ApiResponse(description = "Произошла ошибка", responseCode = "400")
    })
    public ResponseEntity<Boolean> signInEvent(@PathVariable @Parameter(name = "Username участника") String participantNames,
                                               @RequestBody @Parameter(name = "Мероприятие") Event event) {
        boolean b = participantService.SignUpForEvent(event, participantNames);
        return new ResponseEntity<>(b, HttpStatusCode.valueOf(200));
    }
}
