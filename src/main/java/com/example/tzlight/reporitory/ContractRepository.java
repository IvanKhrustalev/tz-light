package com.example.tzlight.reporitory;

import com.example.tzlight.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findByNumber(int number);
    Contract findByAdmin_Id(Long adminId);
}
