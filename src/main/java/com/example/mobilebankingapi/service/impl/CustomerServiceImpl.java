package com.example.mobilebankingapi.service.impl;

import com.example.mobilebankingapi.domain.Customer;
import com.example.mobilebankingapi.domain.CustomerSegment;
import com.example.mobilebankingapi.domain.KYC;
import com.example.mobilebankingapi.dto.customer.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.customer.CustomerResponse;
import com.example.mobilebankingapi.dto.customer.UpdateCustomerRequest;
import com.example.mobilebankingapi.mapper.CustomerMapper;
import com.example.mobilebankingapi.repository.CustomerRepository;
import com.example.mobilebankingapi.repository.CustomerSegmentRepository;
import com.example.mobilebankingapi.repository.KYCRepository;
import com.example.mobilebankingapi.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerSegmentRepository customerSegmentRepository;
    private final KYCRepository kycRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }


    @Override
    public void deleteByPhoneNumber(String phoneNumber) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found"));

        customerRepository.delete(customer);
    }


    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found"));

        customerMapper.toCustomerPartially(updateCustomerRequest,
                customer);

        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
    }


    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        // validate email
        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exists!");
        }

        // Validate phone number
        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Phone number already exists!");
        }

        // Validation national card id
        if (kycRepository.existsByNationalCardId(createCustomerRequest.nationalCardId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "National card ID already exists!");
        }

        // Validation customer segment
        CustomerSegment customerSegment = customerSegmentRepository
                .findBySegment(createCustomerRequest.customerSegment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
                        "Customer segment not found!"));

        // Map data from DTO
        Customer customer = customerMapper.toCustomer(createCustomerRequest);

        // Prepare KYC of customer
        KYC kyc = new KYC();
        kyc.setNationalCardId(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);

        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerSegment(customerSegment);
        customer.setKyc(kyc);


        customer = customerRepository.save(customer);


        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

}
