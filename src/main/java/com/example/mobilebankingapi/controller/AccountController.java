package com.example.mobilebankingapi.controller;

import com.example.mobilebankingapi.domain.Account;
import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import com.example.mobilebankingapi.dto.account.UpdateAccountRequest;
import com.example.mobilebankingapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping
    public List<AccountResponse> findAllAccounts(){
        return accountService.findAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse findAccountByAccountNumber(@PathVariable String accountNumber) {
       return accountService.findAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/{customer}")
    public List<AccountResponse> findAccountByCustomer(@RequestParam String customerId) {
        return accountService.findAccountByCustomer(customerId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{accountNumber}")
    public void deleteAccountByAccountNumber(@PathVariable String accountNumber){
       accountService.deleteAccountByAccountNumber(accountNumber);
    }

    @PatchMapping("/{accountNumber}")
    public AccountResponse updateAccountInfoByAccountNumber(@PathVariable String accountNumber,
                                                            @RequestBody UpdateAccountRequest updateAccountRequest){
        return accountService.updateAccountInfoByAccountNumber(accountNumber, updateAccountRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{accountNumber}")
    public void disableAccountByAccountNumber(@PathVariable String accountNumber) {
         accountService.disableAccountByAccountNumber(accountNumber);
    }
}