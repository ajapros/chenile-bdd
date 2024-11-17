package org.chenile.bdd.priced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "org.chenile.bdd.**.configuration",
		"org.chenile.configuration" })
@EntityScan("org.chenile.bdd.**.model")
@EnableJpaRepositories(basePackages = "org.chenile.bdd.**.configuration.dao")
@EnableTransactionManagement
public class PricedApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PricedApplication.class, args);
	}
}
