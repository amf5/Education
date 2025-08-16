package com.com.Courses.service;

public interface CacheServiceI {

    public void saveVerificationCode(String email, String code) ;
      
   

    public String getVerificationCode(String email);
    public void removeVerificationCode(String email) ; 
    Integer getAttemptCount(String email);

    Integer incrementAttemptCount(String email, Integer currentCount);

}
