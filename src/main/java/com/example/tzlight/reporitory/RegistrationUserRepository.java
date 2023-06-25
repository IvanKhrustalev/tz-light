package com.example.tzlight.reporitory;

import com.example.tzlight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationUserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findByUserName(String userName);
}
