package com.com.Courses.serviceImpl;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.com.Courses.service.CacheServiceI;
@Service
public class CacheServiceImpl implements CacheServiceI{

	private final CacheManager cacheManager;
	
	public CacheServiceImpl(CacheManager cacheManager)
	{
		this.cacheManager=cacheManager;
	}
	@Override
	public void saveVerificationCode(String email, String code) {
		 cacheManager.getCache("emailVerificationCodes").put(email, code);
		
	}

	@Override
	public String getVerificationCode(String email) {
		  return cacheManager.getCache("emailVerificationCodes")
                  .get(email, String.class);
	}

	@Override
	public void removeVerificationCode(String email) {
		 cacheManager.getCache("emailVerificationCodes").evict(email);
		
	}
	 @Override
	    @Cacheable(value = "emailSendAttempts", key = "#email")
	    public Integer getAttemptCount(String email) {
	        return 0; 
	    }

	    @Override
	    @CachePut(value = "emailSendAttempts", key = "#email")
	    public Integer incrementAttemptCount(String email, Integer currentCount) {
	        return currentCount + 1;
	    }
	

}
