package com.example.tzlight.reporitory;

import com.example.tzlight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
    @Query(value = "SELECT c FROM Contract c WHERE c.id =: id")
    User findByContractId(Long id);
}
