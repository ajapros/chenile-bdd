package com.netscoretech.pos.price.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"classpath:com/netscoretech/pos/price/bdd", "classpath:org/chenile/cucumber/rest",
                "classpath:org/chenile/cucumber/security/rest"},
        plugin = {"pretty"}
)
@ActiveProfiles("unittest")
public class CukesRestTest {

}

