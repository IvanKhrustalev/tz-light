package com.example.tzlight.service.impl;

import com.example.tzlight.entity.Role;
import com.example.tzlight.entity.User;
import com.example.tzlight.reporitory.RegistrationUserRepository;
import com.example.tzlight.service.adstract.RegistrationUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationRegistrationUserServiceImpl implements RegistrationUserService {
    private final RegistrationUserRepository registrationUserRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationRegistrationUserServiceImpl(RegistrationUserRepository registrationUserRepository, PasswordEncoder passwordEncoder) {
        this.registrationUserRepository = registrationUserRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = registrationUserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRoles().toString())));
    }
    @Transactional
    @Override
    public boolean register(User user) {
        User userForReg = registrationUserRepository.findUserByEmail(user.getEmail());
        if (userForReg != null) {
            return false;
        }
        if (user.getFlag().equals("isAdmin")) {
            user.setAdmin(true);
            user.setRoles(List.of(new Role(1L, "ROLE_ADMIN")));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            registrationUserRepository.save(user);
        }
        if (user.getFlag().equals("isParticipant")) {
            user.setParticipant(true);
            user.setRoles(List.of(new Role(2L, "ROLE_PARTICIPANT")));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            registrationUserRepository.save(user);
        }
        return true;
    }
    @Override
    public List<User> allUsers() {
        return registrationUserRepository.findAll();
    }
}
