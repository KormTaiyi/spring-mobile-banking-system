package com.example.mobilebankingapi.mapper;

import com.example.mobilebankingapi.domain.Customer;
import com.example.mobilebankingapi.dto.customer.CreateCustomerRequest;
import com.example.mobilebankingapi.dto.customer.CustomerResponse;
import com.example.mobilebankingapi.dto.customer.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Partially update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest,
                             @MappingTarget Customer customer);

    // DTO -> Model
    // Model -> DTO
    // return type is converted | target data
    // parameter is source data
    CustomerResponse fromCustomer(Customer customer);

    @Mapping(source = "customerSegment", target = "customerSegment.segment")
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

}
