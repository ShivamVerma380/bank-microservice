package com.eazybank.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.eazybank.accounts.constants.AccountsConstants;
import com.eazybank.accounts.dto.AccountsDto;
import com.eazybank.accounts.dto.AccountsMsgDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Accounts;
import com.eazybank.accounts.entity.Customer;
import com.eazybank.accounts.exception.CustomerAlreadyExistsException;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountsMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerRepository;
import com.eazybank.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;
import lombok.var;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    
    private final StreamBridge streamBridge; // Autowiring will happen automatically as we have defined allArgsConstructor on AccountsServiceImpl class.

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number "
                +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication (Accounts account, Customer customer) {
        var accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(), customer.getEmail(), customer.getMobileNumber());
        logger.info("Sending Communication request for the details: {}", accountsMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        logger.info("Is communication request Successfully triggered ? : {}", result);
    }

    /**
     * 
     * @param Customer object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        
    
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto!=null) {
            Accounts account = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, account);
            accountsRepository.save(account);
            
            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
        );
        customerRepository.deleteById(customer.getCustomerId());
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated = false;
        if (accountNumber != null) {
            Accounts accounts = accountsRepository.findById(accountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
            );
            accounts.setCommunicationSw(true);
            accountsRepository.save(accounts);
            isUpdated = true;
        }
        return isUpdated;
    }

    
    
}
