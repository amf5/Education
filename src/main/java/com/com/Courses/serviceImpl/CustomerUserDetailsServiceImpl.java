package com.com.Courses.serviceImpl;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.com.Courses.model.CustomerUserDetails;
import com.com.Courses.repository.UserRepository;
@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
private final UserRepository userRepository;
public CustomerUserDetailsServiceImpl(UserRepository userRepository) {
	
	this.userRepository=userRepository;
}
	@Override
	public CustomerUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return new CustomerUserDetails(userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("user not found with email: "+email)));
	}

}
