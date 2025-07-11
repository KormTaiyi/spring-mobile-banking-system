package com.example.mobilebankingapi.repository;

import com.example.mobilebankingapi.doman.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts,Integer> {
}
