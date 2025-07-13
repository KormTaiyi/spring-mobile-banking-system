package com.example.mobilebankingapi.service.impl;

import com.example.mobilebankingapi.domain.Customer;
import com.example.mobilebankingapi.dto.customer.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.customer.CustomerResponse;
import com.example.mobilebankingapi.dto.customer.UpdateCustomerRequest;
import com.example.mobilebankingapi.mapper.CustomerMapper;
import com.example.mobilebankingapi.repository.CustomerRepository;
import com.example.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void deleteByPhoneNumber(String phoneNumber) {
        Customer customer= customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with phone number: " + phoneNumber));
        customerRepository.delete(customer);
        customerMapper.fromCustomer(customer);
    } 

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with phone number: " + phoneNumber));
        customerMapper.toCustomerPartially(updateCustomerRequest,customer);
        customer = customerRepository.save(customer);
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with phone number: " + phoneNumber));
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        // Validate email
        if(customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
        }

        // Validate phone number
        if(customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Phone number already exists");
        }

        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        customer.setAccounts(new ArrayList<>());
        customer.setPhoneNumber(createCustomerRequest.phoneNumber());


        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }
}
