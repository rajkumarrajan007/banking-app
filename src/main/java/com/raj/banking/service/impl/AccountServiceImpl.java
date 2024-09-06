package com.raj.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.raj.banking.dto.AccountDto;
import com.raj.banking.entity.Account;
import com.raj.banking.mapper.AccountMapper;
import com.raj.banking.repository.AccountRepository;
import com.raj.banking.service.AccountService;

import lombok.Data;

@Data
@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does't exist"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does't exist"));
		
		double total=account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does't exist"));
		
		if(account.getBalance() < amount)
		{
			throw new RuntimeException("Insufficient amount");
		}
		double total=account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		                                    .collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does't exist"));
		
		accountRepository.deleteById(id);
	}

}
