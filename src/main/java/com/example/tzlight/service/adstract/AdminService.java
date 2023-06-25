package com.example.tzlight.service.adstract;

import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;

public interface AdminService  {
    Event createEvent(Long contractId, User admin, Event event);
    boolean agreementContract(int contractNumber, String userName) throws Exception;
    User findByUserName(String userName);
}
