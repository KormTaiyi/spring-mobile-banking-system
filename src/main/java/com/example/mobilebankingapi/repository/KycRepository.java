package com.example.mobilebankingapi.repository;

import com.example.mobilebankingapi.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<KYC,Integer> {
}
 