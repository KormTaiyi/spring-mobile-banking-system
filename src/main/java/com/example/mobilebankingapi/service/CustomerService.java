package com.example.mobilebankingapi.service;

import com.example.mobilebankingapi.dto.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.CustomerResponse;
import com.example.mobilebankingapi.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void deleteByPhoneNumber(String phoneNumber);
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequestDto);
    CustomerResponse findByPhoneNumber(String phoneNumber);
    CustomerResponse createNew(CreateCustomerRequest createCustomerRequestDto);
    List<CustomerResponse> findAll();
    CustomerResponse findByIPhoneNumber(String phoneNumber);
}
