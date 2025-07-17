package com.example.mobilebankingapi.repository;

import com.example.mobilebankingapi.domain.KYC;
import org.springframework.data.repository.CrudRepository;

public interface KYCRepository
        extends CrudRepository<KYC, Integer> {

    boolean existsByNationalCardId(String nationalCardId);

}
 