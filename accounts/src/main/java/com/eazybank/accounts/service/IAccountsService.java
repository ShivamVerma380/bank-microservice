package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * 
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);   


    /**
     * 
     * @param mobileNumber- Input mobile number
     * @return AccountDetails based on given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);
}
