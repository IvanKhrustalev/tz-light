package com.example.tzlight.reporitory;

import com.example.tzlight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
