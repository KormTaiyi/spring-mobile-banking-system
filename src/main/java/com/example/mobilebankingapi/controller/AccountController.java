package com.example.mobilebankingapi.controller;

import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import com.example.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse createNew(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createNew(createAccountRequest);
    }

}