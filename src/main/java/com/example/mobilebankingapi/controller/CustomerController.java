package com.example.mobilebankingapi.controller;

import com.example.mobilebankingapi.dto.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.CustomerResponse;
import com.example.mobilebankingapi.dto.UpdateCustomerRequest;
import com.example.mobilebankingapi.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{phoneNumber}")
    public void deleteByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.deleteByPhoneNumber(phoneNumber);
    }

    @PatchMapping("/{phoneNumber}")
    public CustomerResponse updateByPhoneNumber(@PathVariable String phoneNumber,
                                                @RequestBody
                                                    UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateByPhoneNumber(phoneNumber, updateCustomerRequest);
    }

    @GetMapping("/{phoneNumber}")
    public CustomerResponse findByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse createNew(@Valid @RequestBody CreateCustomerRequest createCustomerRequestDto){
        return customerService.createNew(createCustomerRequestDto);
    }
}
