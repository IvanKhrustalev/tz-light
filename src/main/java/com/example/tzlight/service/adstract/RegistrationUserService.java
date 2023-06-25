package com.example.tzlight.service.adstract;

import com.example.tzlight.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface RegistrationUserService extends UserDetailsService {
    boolean register(User user);
    List<User> allUsers();

}
