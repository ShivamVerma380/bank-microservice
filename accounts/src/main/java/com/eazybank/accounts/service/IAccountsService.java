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

    /**
     * 
     * @param CustomerDto object
     * @return boolean indicating if update of Account details is successfull or not.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * 
     * @param mobileNumber- Input mobile number
     * @return boolean indicating if Account details were deleted or not.
     */
    boolean deleteAccount(String mobileNumber);

    /**
     * 
     * @param accountNumber- Long
     * @return boolean indicating if update of communication is successfull or not.
     */
    boolean updateCommunicationStatus(Long accountNumber);
}
