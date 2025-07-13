package com.example.mobilebankingapi.dto.account;

public record UpdateAccountRequest(
        String currency,
        String accountType
) {
}
