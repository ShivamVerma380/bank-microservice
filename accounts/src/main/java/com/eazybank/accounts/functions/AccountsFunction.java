package com.eazybank.accounts.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eazybank.accounts.service.IAccountsService;

@Configuration
public class AccountsFunction {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountsFunction.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService iAccountsService) {
        return accountNumber -> {
            logger.info("Updating communication status for the account number: "+ accountNumber.toString());  
            iAccountsService.updateCommunicationStatus(accountNumber);   
        };
    }

}
