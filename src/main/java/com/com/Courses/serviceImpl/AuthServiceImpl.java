package com.com.Courses.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.com.Courses.domain.RoleEnum;
import com.com.Courses.dto.ActivateAccount;
import com.com.Courses.dto.AuthResponse;
import com.com.Courses.dto.ChangePassword;
import com.com.Courses.dto.Login;
import com.com.Courses.dto.Signup;
import com.com.Courses.model.CustomerUserDetails;
import com.com.Courses.model.User;
import com.com.Courses.repository.UserRepository;
import com.com.Courses.service.AuthServiceI;
import com.com.Courses.service.CacheServiceI;
import com.com.Courses.service.EmailServiceI;
import com.com.Courses.token.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class AuthServiceImpl implements AuthServiceI {

	
private final UserRepository userRepository;

private final EmailServiceI emailService;

private final TokenUtil tokenUtil;

private final AuthenticationManager authenticationManager;

private final PasswordEncoder passwordEncoder;
private final CacheServiceI cacheService;
public AuthServiceImpl(UserRepository userRepository,
		EmailServiceI emailService,TokenUtil tokenUtil,
		PasswordEncoder passwordEncoder,
		AuthenticationManager authenticationManager,
		CacheServiceI cacheService) {
	
	this.emailService=emailService;
	this.tokenUtil=tokenUtil;
	this.userRepository=userRepository;
	this.passwordEncoder=passwordEncoder;
	this.authenticationManager=authenticationManager;
	this.cacheService=cacheService;
	
}
	
	// login
	@Override
	public ResponseEntity<?> login(Login login) throws Exception {
		try {
			   Authentication authentication = authenticationManager.authenticate(
			              new UsernamePasswordAuthenticationToken(login.getUserName(),login.getPassword())
			          );
			    User user = userRepository.findByEmail(login.getUserName()).orElseThrow(() -> new RuntimeException("User not found"));
			        CustomerUserDetails userDetails =new CustomerUserDetails(user);
			        
			        String jwt = tokenUtil.generateToken(userDetails);
			user.setToken(jwt);
			userRepository.save(user);
			AuthResponse authResponse=new AuthResponse(user.getId(),jwt);
			        return new ResponseEntity(authResponse,HttpStatus.ACCEPTED); 

			    } catch (Exception e) {
			        return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
			    }
	}

	
	
	// sign up
	@Override
	public ResponseEntity<?> signup(Signup signup) throws Exception {
Optional<User> user=userRepository.findByEmail(signup.getEmail());
User currentUser=new User();
if(!user.isEmpty()) {
	if(user.get().isAcctivate()) {
		throw new Exception("sorry use another email , this email have acount");
		}else {
			currentUser=user.get();
		}
	
}else {
	currentUser.setEmail(signup.getEmail());
	currentUser.setName(signup.getName());
	currentUser.setPassword(passwordEncoder.encode(signup.getPassword()));
	List<RoleEnum>roles=new ArrayList();
	roles.add(RoleEnum.STUDENT);
	currentUser.setRole(roles);
	currentUser.setAccount("APP");
	currentUser.setAcctivate(false);
	userRepository.save(currentUser);
}
     
      cacheService.getAttemptCount(currentUser.getEmail());
      emailService.sendVerificationEmail(signup.getEmail());
		return new ResponseEntity("check your email now , to Activate your account",HttpStatus.CREATED);
	}

	
	
	
	
	////
	
	
	
	
	// Activate Account
	@Override
	public ResponseEntity<?> activateAccount(ActivateAccount account) throws Exception {
		User user=userRepository.findByEmail(account.getEmail()).orElseThrow(()-> new Exception("user not found"));
		String code=cacheService.getVerificationCode(account.getEmail());
		if(code.equals(account.getCode())) {
			user.setAcctivate(true);
			cacheService.removeVerificationCode(account.getEmail());
			
		}
		CustomerUserDetails userDetails=new CustomerUserDetails(user);
		String jwt = tokenUtil.generateToken(userDetails);
		user.setToken(jwt);
		userRepository.save(user);
		AuthResponse authResponse=new AuthResponse(user.getId(),jwt);
		return new ResponseEntity(authResponse,HttpStatus.ACCEPTED) ;
	}

	
	
	
	
	
	
	
	
	
	@Override
	public ResponseEntity<?> resentCode(String email) throws Exception {
	cacheService.removeVerificationCode(email);
	Integer num=cacheService.getAttemptCount(email);
	if(num>=5) {
		
		throw new Exception("try after hour from now");
	}
cacheService.incrementAttemptCount(email, num);
	emailService.sendVerificationEmail(email);
	
	
	return new ResponseEntity ("code is sent ",HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> logout(HttpServletRequest request) {
		 try {
	         
	         String token = tokenUtil.getTokenFromRequest(request);
	         if (token == null || token.isEmpty()) {
	             return ResponseEntity.status(400).body("Token is missing or invalid");
	         }

	         
	         String email = tokenUtil.getEmailFromToken(token);
	         if (email == null || email.isEmpty()) {
	             return ResponseEntity.status(400).body("Invalid token: Unable to extract email");
	         }

	         Optional<User> user = userRepository.findByEmail(email);
	         if (user.isPresent()) {
	             
	             user.get().setToken(null);
	             userRepository.save(user.get()); 
	         } else {
	             return ResponseEntity.status(401).body("User not found or unauthorized");
	         }

	        
	         return ResponseEntity.ok().body("Successful logout");
	     } catch (Exception e) {
	         
	         return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
	     }
	 }



	 
	 
	 

	@Override
	public ResponseEntity<?> forgotMyPassword(String email) throws Exception {
		User user=userRepository.findByEmail(email).orElseThrow(()->new Exception("user not found"));
		emailService.sendVerificationPassword(email);
		return new ResponseEntity("check your email now!",HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> changeMyPassword(ChangePassword changeMyPassword) throws Exception {
		User user=userRepository.findByEmail(changeMyPassword.getEmail()).orElseThrow(()->new Exception("user not found"));
		user.setPassword(passwordEncoder.encode(changeMyPassword.getPassword()));
		userRepository.save(user);
		return new ResponseEntity("password is changed successfully",HttpStatus.ACCEPTED);
	}

}
