package com.example.mobilebankingapi.dto.account;

import java.math.BigDecimal;

public record AccountResponse(
        String actNo,
        String actName,
        String actCurrency,
        BigDecimal balance,
        String accountType,
        Boolean isHide
) {
}
