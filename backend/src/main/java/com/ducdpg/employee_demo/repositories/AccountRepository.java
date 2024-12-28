package com.ducdpg.employee_demo.repositories;

import com.ducdpg.employee_demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
