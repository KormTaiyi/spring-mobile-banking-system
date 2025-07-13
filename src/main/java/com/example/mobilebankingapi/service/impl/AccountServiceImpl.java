package com.example.mobilebankingapi.service.impl;

import com.example.mobilebankingapi.domain.Account;
import com.example.mobilebankingapi.dto.account.AccountResponse;
import com.example.mobilebankingapi.dto.account.CreateAccountRequest;
import com.example.mobilebankingapi.dto.account.UpdateAccountRequest;
import com.example.mobilebankingapi.mapper.AccountMapper;
import com.example.mobilebankingapi.repository.AccountRepository;
import com.example.mobilebankingapi.repository.CustomerRepository;
import com.example.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        if(accountRepository.existsByAccountNumber(createAccountRequest.accountNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Account already exists with account number: " + createAccountRequest.accountNumber());
        }
        Account account = accountMapper.toAccountResponse(createAccountRequest);
        account = accountRepository.save(account);
        return accountMapper.fromAccounts(account);
    }

    @Override
    public List<AccountResponse> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map(accountMapper::fromAccounts)
                .toList();
    }

    @Override
    public AccountResponse findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(accountMapper::fromAccounts)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account not found with account number: " + accountNumber));
    }

    @Override
    public List<AccountResponse> findAccountByCustomer(String customerId) {
        if(!customerRepository.existsByCustomerId(customerId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer not found with id: " + customerId);
        }
        return accountRepository.findByCustomerId(customerId).stream().map(accountMapper::fromAccounts).toList();
    }

    @Override
    public void deleteAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Account not found with account number: " + accountNumber));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponse updateAccountInfoByAccountNumber(String accountNumber, UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with account number: " + accountNumber));
        accountMapper.toCustomerPartially(updateAccountRequest, account);
        account = accountRepository.save(account);
        return accountMapper.fromAccounts(account);
    }

    @Override
    public AccountResponse disableAccountByAccountNumber(String accountNumber,UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with account number: " + accountNumber));
        account.setIsDeleted(true);
        account = accountRepository.save(account);
        return accountMapper.fromAccounts(account);
    }
}
