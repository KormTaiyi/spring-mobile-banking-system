package com.example.mobilebankingapi.dto.account;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        BigDecimal initialBalance,
        String currency,
        String accountType
) {
}
