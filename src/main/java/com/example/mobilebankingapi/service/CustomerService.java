package com.example.mobilebankingapi.service;

import com.example.mobilebankingapi.dto.customer.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.customer.CustomerResponse;
import com.example.mobilebankingapi.dto.customer.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void deleteByPhoneNumber(String phoneNumber);
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequestDto);
    CustomerResponse findByPhoneNumber(String phoneNumber);
    CustomerResponse createNew(CreateCustomerRequest createCustomerRequestDto);
    List<CustomerResponse> findAll();
}
