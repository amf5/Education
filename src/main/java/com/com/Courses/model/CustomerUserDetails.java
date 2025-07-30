package com.com.Courses.model;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerUserDetails implements UserDetails {
	private User user;

	public CustomerUserDetails(User user) {
		
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return user.getRole()!=null?user.getRole().stream().map(role->
		new SimpleGrantedAuthority("ROLE_" + role.toString()))
				.collect(Collectors.toList()):
			Collections.EMPTY_LIST;
				
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}
	
	public Long getId() {
		
		return user.getId();
	}

	@Override
	public String getUsername() {
		
		return user.getName();
	}
	
	public String getEmail() {
		
		return user.getEmail();
	}
	@Override
	public boolean isEnabled() {
				return user.isAcctivate();
	}
	
	public String getAccount() {
		return user.getAccount();
	}

	

}
