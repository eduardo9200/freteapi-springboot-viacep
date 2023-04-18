package com.wipro.freteapi.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/features",
	plugin = {"pretty"},
	glue = {"com.wipro.freteapi.cucumber.cucumberglue"}
)
public class FreteapiApplicationCucumberTests {}
