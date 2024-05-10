package com.eazybank.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybank.accounts.dto.LoansDto;
import com.eazybank.accounts.service.client.LoansFeignClient;

@Component
public class LoansFallback implements LoansFeignClient{

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
        return null;
    }
    
}
