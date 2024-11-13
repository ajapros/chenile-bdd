package org.chenile.cucumber.security.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import org.chenile.cucumber.CukesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Cucumber steps to facilitate injection of security tokens into the MVC request header.<br/>
 * See the methods  below for the precise Gherkin language that has been created.
 */
@ActiveProfiles("unittest")
@AutoConfigureMockMvc
public class RestCukesSecSteps {


    @Value("${chenile.security.keycloak.host}")
    private String keycloakHost;

    @Value("${chenile.security.keycloak.base.realm}")
    private String tenant;


    protected final ObjectMapper objectMapper = new ObjectMapper();

    CukesContext context = CukesContext.CONTEXT;

    @When("I construct a REST request with authorization header in realm {string} for user {string} and password {string}")
    public void i_construct_an_authorized_REST_request_in_realm_for_user_and_password
            (String realm, String user, String password) {

        Map<String, String> headers = context.get("headers");
        if (headers == null) {
            headers = new HashMap<>();
            context.set("headers", headers);
        }
        String headerName = "Authorization";
        String headerValue = "Bearer " + getToken(realm,user,password);
        headers.put(headerName, headerValue);
    }

    public  String getToken(String realm, String user, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", Collections.singletonList("password"));
        map.put("client_id", Collections.singletonList("authz-servlet"));
        map.put("client_secret", Collections.singletonList("secret"));
        /*List<String> list = new ArrayList<>();
        list.add("profile"); list.add("openid"); list.add("email");
        map.put("scope", list);*/
        map.put("username", Collections.singletonList(user));
        map.put("password", Collections.singletonList(password));

        String authServerUrl = keycloakHost +
                "/realms/" + realm + "/protocol/openid-connect/token";
        var request = new HttpEntity<>(map, httpHeaders);
        KeyCloakToken token = restTemplate.postForObject(
                authServerUrl,
                request,
                KeyCloakToken.class
        );

        assert token != null;
        return token.accessToken();
    }

    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {
    }
}