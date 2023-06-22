package com.manage.employee.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manage.employee.entity.Account;
import com.manage.employee.repository.AccountRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.getAccountByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("Cannot find account with this given username: " + username);
		}
		return new CustomUserDetails(account);
	}

}
