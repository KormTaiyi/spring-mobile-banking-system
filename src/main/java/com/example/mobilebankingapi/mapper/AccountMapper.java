package com.example.mobilebankingapi.mapper;

import com.example.mobilebankingapi.domain.Account;
import com.example.mobilebankingapi.domain.Customer;
import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import com.example.mobilebankingapi.dto.account.UpdateAccountRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    AccountResponse fromAccounts(Account accounts);
    Account toAccountResponse(CreateAccountRequest createAccountRequest);
}
