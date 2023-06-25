package com.example.tzlight.service.impl;

import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;
import com.example.tzlight.reporitory.EventRepository;
import com.example.tzlight.reporitory.ParticipantRepository;
import com.example.tzlight.service.adstract.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean SignUpForEvent(Event event, String... participantNames) {
        List<User> paticipantList = new ArrayList<>();
        for (String participantName : participantNames) {
            User participantByUserName = participantRepository.findByUserName(participantName);
            if (participantByUserName.isParticipant()) {
                paticipantList.add(participantByUserName);
            }
        }
        event.setParticipants(paticipantList);
        eventRepository.save(event);
        return true;
    }
}
