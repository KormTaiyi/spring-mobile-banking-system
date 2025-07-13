package com.example.mobilebankingapi.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateCustomerRequest(

        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "Gender is required")
        String gender,

        String email,
        String phoneNumber,
        String remark
) {
}
