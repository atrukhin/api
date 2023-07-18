package api.message.weather.response;

import api.message.AbstractResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class Success extends AbstractResponse {
    private Request request;
    private Location location;
    private Current current;

    public Success() {
        params.put("query", x -> request.query);
        params.put("type", x -> request.type);
        params.put("language", x -> request.language);
        params.put("name", x -> location.name);
        params.put("country", x -> location.country);
        params.put("utcOffset", x -> location.utcOffset);
        params.put("temperature", x -> String.valueOf(current.temperature));
        params.put("windSpeed", x -> String.valueOf(current.windSpeed));
    }

    @Getter
    private static class Request {
        private String type;
        private String query;
        private String language;
        private String unit;
    }

    @Getter
    private static class Current {
        private String observationTime;
        private Integer temperature;
        private Integer weatherCode;
        private List<String> weatherIcons;
        private List<String> weatherDescriptions;
        private Integer windSpeed;
        private Integer windDegree;
        private String windDir;
        private Integer pressure;
        private Integer precip;
        private Integer humidity;
        private Integer cloudcover;
        private Integer feelslike;
        private Integer uvIndex;
        private Integer visibility;
        private String isDay;
    }

    @Getter
    private static class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezoneId;
        private String localtime;
        private Integer localtimeEpoch;
        private String utcOffset;
    }
}
