package com.insuranceservice.configuration;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.insuranceservice.util.Constants;

@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig implements Constants {

	@CacheEvict(value = CACHE_NAME, allEntries = true)
	@Scheduled(fixedRateString = "${caching.spring.hmTTL}")
	public void clearCache() {

	}

}
