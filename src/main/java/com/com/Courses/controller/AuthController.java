package com.com.Courses.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.Courses.dto.ActivateAccount;
import com.com.Courses.dto.ChangePassword;
import com.com.Courses.dto.EmailRequest;
import com.com.Courses.dto.Login;
import com.com.Courses.dto.Signup;
import com.com.Courses.service.AuthServiceI;
import com.com.Courses.token.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/education")

public class AuthController {
	private final AuthServiceI authService;
	private final TokenUtil tokenUtil;
	
	
	public AuthController(AuthServiceI authService,
			TokenUtil tokenUtil) {
		this.authService=authService;
		this.tokenUtil=tokenUtil;
		
	}

	
	  @PostMapping("/login")
	    public ResponseEntity<?> login( @RequestBody @Valid Login login) throws Exception {
	        return authService.login(login);
	    }

	    
	    @PostMapping("/signup")
	    public ResponseEntity<?> signup( @RequestBody @Valid Signup signup) throws Exception {
	        return authService.signup(signup);
	    }

	    @PostMapping("/activate")
	    public ResponseEntity<?> activateAccount( @RequestBody @Valid ActivateAccount activateAccount) throws Exception {
	        return authService.activateAccount(activateAccount);
	    }

	    @PostMapping("/resentcode")
	    public ResponseEntity<?> resent(  @RequestBody @Valid EmailRequest email) throws Exception {
	        return authService.resentCode(email.getEmail());
	        }

	   @PostMapping("/logout")
	   public ResponseEntity<?> logout(HttpServletRequest request) {
	      return authService.logout(request);
	   }

	   

	   @PostMapping("/forgot")
	   public ResponseEntity<?> forgot( @RequestBody @Valid EmailRequest email) throws Exception {
	      return authService.forgotMyPassword(email.getEmail());
	   }
	   @PatchMapping("/change/{id}")
	   public ResponseEntity<?> change(  @RequestBody @Valid ChangePassword changeMyPassword,
			   @RequestHeader("Authorization")String jwt,@PathVariable Long id) throws Exception {
		   Long userId=tokenUtil.getIdFromBearerJwt(jwt);
	
		   
		    if (!userId.equals(id) || id == null) {
		        throw new Exception("Error....you don't have this id");
		    }
	
	      return authService.changeMyPassword(changeMyPassword);
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
