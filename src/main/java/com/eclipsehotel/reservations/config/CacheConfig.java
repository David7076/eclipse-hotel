package com.eclipsehotel.reservations.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CEP_ADDRESS_CACHE = "cep-address";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CEP_ADDRESS_CACHE);
    }

}
