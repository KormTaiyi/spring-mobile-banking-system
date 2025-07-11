package com.example.mobilebankingapi.service.impl;

import com.example.mobilebankingapi.doman.Customer;
import com.example.mobilebankingapi.dto.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.CustomerResponse;
import com.example.mobilebankingapi.dto.UpdateCustomerRequest;
import com.example.mobilebankingapi.mapper.CustomerMapper;
import com.example.mobilebankingapi.repository.CustomerRepository;
import com.example.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

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
        customerMapper.toCustomerPartially(customer,updateCustomerRequest);
        customer = customerRepository.save(customer);
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customer -> CustomerResponse.builder()
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with phone number: " + phoneNumber));
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequestDto) {

        // Validate email
        if(customerRepository.existsByEmail(createCustomerRequestDto.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already exists");
        }

        // Validate phone number
        if(customerRepository.existsByPhoneNumber(createCustomerRequestDto.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Phone number already exists");
        }

        Customer customer = new Customer();
        customer.setFullName(createCustomerRequestDto.fullName());
        customer.setGender(createCustomerRequestDto.gender());
        customer.setEmail(createCustomerRequestDto.email());
        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());
        customer.setPhoneNumber(createCustomerRequestDto.phoneNumber());

        log.info("Customer before saving: {}",customer.getId());

        customer = customerRepository.save(customer);

        log.info("Customer before saving: {}",customer.getId());

        return CustomerResponse.builder()
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .gender(customer.getGender())
                .build();
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers
                .stream()
                .map(customer -> CustomerResponse.builder()
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .gender(customer.getGender())
                        .build())
                .toList();
    }
    @Override
    public CustomerResponse findByIPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .map(customer -> CustomerResponse.builder()
                        .fullName(customer.getFullName())
                        .email(customer.getEmail())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with phone number: " + phoneNumber));
    }
}
