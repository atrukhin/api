package api.message.weather.response;

import api.message.AbstractResponse;
import lombok.Getter;

@Getter
public class Fail extends AbstractResponse {
    private Boolean success;
    private Error error;

    public Fail() {
        params.put("success", x -> String.valueOf(success));
        params.put("code", x -> String.valueOf(error.code));
        params.put("type", x -> error.type);
        params.put("info", x -> error.info);
    }

    @Getter
    private static class Error {
        private Integer code;
        private String type;
        private String info;
    }
}
