package api.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

public final class HttpAdapter {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final long REQUEST_TIMEOUT_IN_SECONDS = 5L;
    private static final HttpResponse.BodyHandler<String> STRING_BODY_HANDLER =
            HttpResponse.BodyHandlers.ofString();
    private static final String PARAM_JOINER = "=";
    private static final String PARAM_DELIMITER = "&";

    private HttpAdapter() {
    }

    public static class URL {
        private final String scheme;
        private final String host;
        private final int port;
        private final String path;

        public URL(String scheme, String host, int port, String path) {
            this.scheme = scheme;
            this.host = host;
            this.port = port;
            this.path = path;
        }
    }

    public static String fetch(URL url, Map<String, String> params) {
        String query = toQuery(params);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().GET()
                .timeout(Duration.of(REQUEST_TIMEOUT_IN_SECONDS, ChronoUnit.SECONDS));
        try {
            URI uri = new URI(url.scheme, null, url.host, url.port, url.path, query, null);
            HttpRequest httpRequest = requestBuilder.uri(uri).build();
            return client.send(httpRequest, STRING_BODY_HANDLER).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toQuery(Map<String, String> params) {
        return params.entrySet().stream()
                .map(e -> e.getKey() + PARAM_JOINER + e.getValue())
                .collect(Collectors.joining(PARAM_DELIMITER));
    }
}
