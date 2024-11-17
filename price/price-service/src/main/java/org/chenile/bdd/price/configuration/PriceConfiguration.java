package org.chenile.bdd.price.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.chenile.bdd.price.service.PriceService;
import org.chenile.bdd.price.service.impl.PriceServiceImpl;
import org.chenile.bdd.price.service.healthcheck.PriceHealthChecker;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class PriceConfiguration {
	@Bean public PriceService _priceService_() {
		return new PriceServiceImpl();
	}

	@Bean PriceHealthChecker priceHealthChecker(){
    	return new PriceHealthChecker();
    }
}
