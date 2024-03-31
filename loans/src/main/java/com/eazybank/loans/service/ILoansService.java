package com.eazybank.loans.service;

import com.eazybank.loans.dto.LoansDto;

public interface ILoansService {

    /**
     * 
     * @param mobileNumber - Mobile number of the customer
     */
    void createLoan (String mobileNumber);

    /**
     * 
     * @param mobileNumber - Input mobile number
     * @return Loan Details based on mobile number
     */
    LoansDto fetchLoanDetails (String mobileNumber);

    /**
     * 
     * @param loansDto - LoansDto object
     * @return boolean indicating if update of loan details is successfull or not
     */
    boolean updateLoan (LoansDto loansDto);

    /**
     * 
     * @param loansDto - Input mobile number
     * @return boolean indicating if delete of loan details is successfull or not
     */
    boolean deleteLoan (String mobileNumber);
    
}
