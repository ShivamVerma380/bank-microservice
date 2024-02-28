package com.eazybank.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybank.accounts.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long>{
        
}
