package api.steps;

import api.WeatherTest;
import api.message.weather.request.Current;
import api.message.weather.response.Fail;
import api.message.weather.response.Success;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class WeatherSteps extends AbstractSteps {

    @When("Request is sent for current weather:")
    public void current(@Transpose Map<String, String> params) {
        send(params, Current.class, WeatherTest.url(Current.PATH));
    }

    @Then("Checking success response:")
    public void success(@Transpose Map<String, String> expected) {
        verify(expected, Success.class, regexEqualizer);
    }

    @Then("Checking fail response:")
    public void fail(@Transpose Map<String, String> expected) {
        verify(expected, Fail.class);
    }
}
