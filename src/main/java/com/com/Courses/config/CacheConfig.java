package com.com.Courses.config;

import com.github.benmanes.caffeine.cache.Caffeine; 
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit; 

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
       
        CaffeineCache emailVerificationCache = new CaffeineCache(
                "emailVerificationCodes", 
                Caffeine.newBuilder()
                        .expireAfterWrite(5, TimeUnit.MINUTES)
                        .maximumSize(1000) 
                        .build()
        );

        

      
        CaffeineCache emailSendAttemptCache = new CaffeineCache(
                "emailSendAttempts",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.HOURS)
                        .maximumSize(1000)
                        .build()
        );

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(emailVerificationCache, emailSendAttemptCache));
        return manager;
    }
}
