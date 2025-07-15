package com.example.mobilebankingapi.repository;

import com.example.mobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    List<Account> findAllByIsDisabledFalse();

    boolean isExistsByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
//    boolean existsByCustomerId(Long customerId);
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByCustomerId(String customerId);
}
