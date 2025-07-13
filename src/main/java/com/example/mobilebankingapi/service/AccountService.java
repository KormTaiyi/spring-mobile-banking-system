package com.example.mobilebankingapi.service;

import com.example.mobilebankingapi.domain.Account;
import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import com.example.mobilebankingapi.dto.account.UpdateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAllAccounts();
    AccountResponse findAccountByAccountNumber(String accountNumber);
    List<AccountResponse> findAccountByCustomer(String customerId);
    void deleteAccountByAccountNumber(String accountNumber);
    AccountResponse updateAccountInfoByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest);
    AccountResponse disableAccountByAccountNumber(String accountNumber,UpdateAccountRequest updateAccountRequest);
}
