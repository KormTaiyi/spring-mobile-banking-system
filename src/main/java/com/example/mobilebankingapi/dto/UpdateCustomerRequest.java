package com.example.mobilebankingapi.dto;


public record UpdateCustomerRequest(

        String fullName,
        String gender,
        String remark
) {
}
