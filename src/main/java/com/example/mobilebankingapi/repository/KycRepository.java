package com.example.mobilebankingapi.repository;

import com.example.mobilebankingapi.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KycRepository extends JpaRepository<KYC,Integer> {
    Optional<KYC> findByCustomerId(UUID customerId);
}
 