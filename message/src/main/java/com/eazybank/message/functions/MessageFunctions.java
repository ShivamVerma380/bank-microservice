package com.eazybank.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.eazybank.message.dto.AccountsMsgDto;

@Configuration
public class MessageFunctions {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
            logger.info("Sending email with the details: "+ accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            logger.info("Sending sms with the details: "+ accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }

}
