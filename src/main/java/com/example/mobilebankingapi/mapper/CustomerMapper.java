package com.example.mobilebankingapi.mapper;

import com.example.mobilebankingapi.domain.Customer;
import com.example.mobilebankingapi.dto.customer.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.customer.CustomerResponse;
import com.example.mobilebankingapi.dto.customer.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);

    CustomerResponse fromCustomer(Customer customer);
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
}
