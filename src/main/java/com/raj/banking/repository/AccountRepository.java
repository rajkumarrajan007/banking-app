package com.raj.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raj.banking.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Long> {

}
