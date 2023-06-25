package com.example.tzlight.service.adstract;

import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;

public interface ParticipantService {
    boolean SignUpForEvent(Event event, String... participantName);
}
