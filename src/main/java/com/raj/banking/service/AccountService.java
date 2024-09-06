package com.raj.banking.service;

import java.util.List;

import com.raj.banking.dto.AccountDto;

public interface AccountService {
	//method
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
