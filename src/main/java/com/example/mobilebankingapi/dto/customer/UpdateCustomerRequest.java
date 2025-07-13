package com.example.mobilebankingapi.dto.customer;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
