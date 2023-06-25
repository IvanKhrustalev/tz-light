package com.example.tzlight.service.impl;

import com.example.tzlight.entity.Contract;
import com.example.tzlight.entity.Event;
import com.example.tzlight.entity.User;
import com.example.tzlight.exception.NotAdminException;
import com.example.tzlight.reporitory.AdminRepository;
import com.example.tzlight.reporitory.ContractRepository;
import com.example.tzlight.reporitory.EventRepository;
import com.example.tzlight.reporitory.RegistrationUserRepository;
import com.example.tzlight.service.adstract.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AdminServiceImpl implements AdminService {
    private final RegistrationUserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ContractRepository contractRepository;
    private final EventRepository eventRepository;

    public AdminServiceImpl(RegistrationUserRepository userRepository, AdminRepository adminRepository, ContractRepository contractRepository,
                            EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.contractRepository = contractRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Long contractId, User admin, Event event) {
        User adminByContractId = adminRepository.findByContractId(contractId);
        if (adminByContractId.isAdmin()) {
           return eventRepository.save(event);
        } else try {
            throw new NotAdminException("Не найдено Администратора, подписавшего договор!");
        } catch (NotAdminException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean agreementContract(int contractNumber, String userName) {
        Contract contractToAgreement = contractRepository.findByNumber(contractNumber);
        User admin = userRepository.findByUserName(userName);
        if (admin.isAdmin()) {
            contractToAgreement.setStatus("OK");
            contractToAgreement.setAdmin(admin);
            contractRepository.save(contractToAgreement);
            return true;
        }
        else try {
            throw new NotAdminException("Извините, Вы не являетесь администратором!");
        } catch (NotAdminException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByUserName(String userName) {
        return adminRepository.findByUserName(userName);
    }
}
