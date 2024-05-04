package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Customer;

public class CustomerMapper {
    
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto (Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setName(customer.getName());
        return customerDetailsDto;
    }
}
