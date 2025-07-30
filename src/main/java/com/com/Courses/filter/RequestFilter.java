package com.com.Courses.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.com.Courses.model.CustomerUserDetails;
import com.com.Courses.repository.UserRepository;
import com.com.Courses.serviceImpl.CustomerUserDetailsServiceImpl;
import com.com.Courses.token.TokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class RequestFilter extends OncePerRequestFilter {
private final TokenUtil tokenUtil;
private final CustomerUserDetailsServiceImpl userService;
private final UserRepository userRepository;

public RequestFilter(TokenUtil tokenUtil,CustomerUserDetailsServiceImpl userService, UserRepository userRepository)
{
this.tokenUtil=tokenUtil;	

this.userService=userService;
this.userRepository=userRepository;
}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String token = getTokenFromRequest(request);
        String username = null;

        if (token != null ) {
        
        	username = tokenUtil.getEmailFromToken(token);
        String storeToken=userRepository.findTokenByEmail(username);
        if(storeToken!=null) {
        	if( tokenUtil.validateToken(token, userService.loadUserByUsername(username))&&
        			storeToken.equals(token)
        			)
        	{
        		
           
            CustomerUserDetails userDetails = (CustomerUserDetails) userService.loadUserByUsername(username);


           
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails.getEmail(), null, userDetails.getAuthorities()));
        }}
        	
        }
       

       
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
		
	}

