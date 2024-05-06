package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     * 
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails (String mobileNumber, String correlationId);
    
}
