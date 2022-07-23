package automationcucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/",
        tags = "@smoke4",
        plugin = {"html:target/automation-reports"})
public class TestRunner
{

}
