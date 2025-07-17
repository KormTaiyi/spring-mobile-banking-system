package com.example.mobilebankingapi.service;

import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;

public interface AccountService {

    AccountResponse createNew(CreateAccountRequest createAccountRequest);

}
