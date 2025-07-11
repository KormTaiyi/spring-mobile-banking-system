package com.example.mobilebankingapi.mapper;

import com.example.mobilebankingapi.doman.Customer;
import com.example.mobilebankingapi.dto.CustomerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
            void toCustomerPartially(Customer customer, CustomerResponse customerResponseDto);

    CustomerResponse toCustomerResponseDto(Customer customer);
    CustomerResponse fromCustomer(Customer customer);
    Customer toCustomer(CustomerResponse customerResponseDto);
}
