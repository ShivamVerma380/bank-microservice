package com.eazybank.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.eazybank.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{
    
    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional  //run it in transaction... any partial data deleted and if any error occurs further, then the partial data will be rolled back.
    @Modifying  // This method will modify the data in database
    void deleteByCustomerId(Long customerId);
}
