package api.message.weather.request;

import api.WeatherTest;
import api.message.AbstractRequest;
import lombok.Getter;

@Getter
public class Current extends AbstractRequest {
    public static final String PATH = "/current";
    private String query;
    private String units;
    private String language;

    public Current() {
        params.put("access_key", x -> WeatherTest.ACCESS_KEY);
        params.put("query", x -> query);
        params.put("units", x -> units);
        params.put("language", x -> language);
    }
}
