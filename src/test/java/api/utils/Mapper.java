package api.utils;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.util.Map;

public final class Mapper {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    private Mapper() {
    }

    public static <K, V, T> T fromMap(Map<K, V> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new AssertionError(e);
        }
    }
}
