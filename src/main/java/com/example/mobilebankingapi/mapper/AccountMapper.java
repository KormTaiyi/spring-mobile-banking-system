package com.example.mobilebankingapi.mapper;

import com.example.mobilebankingapi.domain.Account;
import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "actCurrency", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountType.type", target = "accountType")
    AccountResponse fromAccount(Account account);
}
