package ru.ibs.framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.ibs.framework.utils.MyAllureListener"},
        glue = {"ru.ibs.framework.steps"},
        features = {"src/test/resources/"},
        tags = {"@secondTest"}
)
public class CucumberRunner {
}