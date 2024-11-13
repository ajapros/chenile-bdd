package com.netscoretech.pos.price;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;


@Configuration
@PropertySource("classpath:com/netscoretech/pos/price/TestService.properties")
@SpringBootApplication(scanBasePackages = {"org.chenile.configuration", "com.netscoretech.pos.**"})
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer {


}

