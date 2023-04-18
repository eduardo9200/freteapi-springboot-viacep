package com.wipro.freteapi.cucumber.cucumberglue;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberSteps {

    @LocalServerPort
    String port;
    ResponseEntity<String> lastResponse;

    @When("the client calls endpoint {string}")
    public void whenClientCalls(String url) {
        try {
            lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @Then("response status code is {int}")
    public void thenStatusCodee(int expected) {
        Assertions.assertNotNull(lastResponse);
        Assertions.assertNotNull(lastResponse.getStatusCode());
        assertThat("status code is" + expected,
                lastResponse.getStatusCodeValue() == expected);
    }

    @Then("response status code is not present")
    public void thenStatusCodeIsNotPresent() {
        Assertions.assertNull(lastResponse);
    }

    @Then("returned string should be {string}")
    public void thenStringIs(String expected) {
        Assertions.assertEquals(expected, lastResponse.getBody());
    }
}
