package com.example.mobilebankingapi.dto.customer;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String fullName,
        String gender,
        String email
) {
}
