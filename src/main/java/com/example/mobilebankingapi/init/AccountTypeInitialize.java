package com.example.mobilebankingapi.init;

import com.example.mobilebankingapi.domain.AccountType;
import com.example.mobilebankingapi.repository.AccountTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountTypeInitialize {

    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init() {
        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setType("PAYROLL");

            AccountType saving = new AccountType();
            saving.setType("SAVING");

            AccountType junior = new AccountType();
            junior.setType("JUNIOR");

            accountTypeRepository.saveAll(List.of(payroll, saving, junior));
        }
    }

}