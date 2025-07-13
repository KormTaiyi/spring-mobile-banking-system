package com.example.mobilebankingapi.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateAccountRequest(

        @NotBlank(message = "Account type is required")
        String accountType,

        @NotBlank(message="Currency is required")
        String currency,

        BigDecimal initialBalance,

        String accountNumber,
        String ownerPhoneNumber

) {
}
