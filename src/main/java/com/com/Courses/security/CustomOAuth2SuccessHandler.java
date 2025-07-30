 package com.com.Courses.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.com.Courses.domain.RoleEnum;
import com.com.Courses.dto.AuthResponse;
import com.com.Courses.model.CustomerUserDetails;
import com.com.Courses.model.User;
import com.com.Courses.repository.UserRepository;
import com.com.Courses.token.TokenUtil;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
 public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
private final TokenUtil tokenUtil;
private final UserRepository userRepository;
    
     public CustomOAuth2SuccessHandler(TokenUtil tokenUtil,
    		 UserRepository userRepository) {
         this.tokenUtil=tokenUtil;
         this.userRepository=userRepository;
     }

     @Override
     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
             throws IOException {
         try {
             OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();

             String email = oauthUser.getAttribute("email");
             String name = oauthUser.getAttribute("name");

             User user = userRepository.findByEmail(email)
                     .map(u -> {
                         if ("APP".equals(u.getAccount())) {
                             throw new IllegalStateException("this email have account by sign up APPlication, go to login by this account");
                         }
                         return u;
                     })
                     .orElse(null);

             if (user == null) {
                 user = new User();
                 user.setAccount("GOOGLE");
                 user.setEmail(email);
                 user.setName(name);
                 List<RoleEnum> roles = new ArrayList<>();
                 roles.add(RoleEnum.STUDENT);
                 user.setRole(roles);
                 user.setAcctivate(true);
             }

             String token = tokenUtil.generateToken(new CustomerUserDetails(user));
             user.setToken(token);
             User newUser = userRepository.save(user);

             AuthResponse authResponse = new AuthResponse(newUser.getId(), token);
             response.setContentType("application/json");
             response.setCharacterEncoding("UTF-8");
             response.getWriter().write(
                     "{\"id\":\"" + authResponse.getId() + "\", \"token\":\"" + authResponse.getToken() + "\"}");
         } catch (IllegalStateException e) {
             response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
         } catch (Exception e) {
             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong.");
         }
     }

 }
