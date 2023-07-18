package api;

import api.utils.HttpAdapter;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;

@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@Weather")
public class WeatherTest extends AbstractTest {
    private static final String SCHEME = "http";
    private static final String HOST = "api.weatherstack.com";
    private static final int PORT = 80;
    public static final String ACCESS_KEY = "a0f768b50231ffcfc670f74103a3ae9a";

    public static HttpAdapter.URL url(String path) {
        return new HttpAdapter.URL(SCHEME, HOST, PORT, path);
    }
}
