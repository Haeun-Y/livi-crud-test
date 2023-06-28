package com.example.demo.domain.account.repository;

import com.example.demo.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
