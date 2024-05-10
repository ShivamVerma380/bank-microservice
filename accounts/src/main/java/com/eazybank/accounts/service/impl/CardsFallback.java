package com.eazybank.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybank.accounts.dto.CardsDto;
import com.eazybank.accounts.service.client.CardsFeignClient;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
    
}
