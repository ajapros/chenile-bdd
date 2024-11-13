package com.netscoretech.pos.price.bdd;

import com.netscoretech.pos.price.SpringTestConfig;
import cucumber.api.java.en.Given;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;


/**
    * This "steps" file's purpose is to hook up the SpringConfig to the test case.
    * It does not contain any methods currently but can be used for writing your own custom BDD steps
    * if required. In most cases people don't need additional steps since cucumber-utils provides for
    * most of the steps. <br/>
    * This class requires a dummy method to keep Cucumber from erring out. (Cucumber needs at least
    * one step in a steps file)<br/>
*/
@SpringBootTest(classes = SpringTestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("unittest")
public class CukesSteps {
	
	@Given("dummy") public void dummy(){}
}
