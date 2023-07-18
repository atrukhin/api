package api.steps;

import api.message.AbstractRequest;
import api.message.AbstractResponse;
import api.utils.Differ;
import api.utils.HttpAdapter;
import api.utils.Mapper;
import api.utils.RegexEqualizer;
import io.cucumber.datatable.internal.difflib.myers.Equalizer;

import java.util.Map;

public abstract class AbstractSteps {
    public static final Equalizer<String> regexEqualizer = new RegexEqualizer();
    private String result;

    public void send(Map<String, String> params, Class<? extends AbstractRequest> clazz,
                     HttpAdapter.URL url) {
        AbstractRequest request = Mapper.fromMap(params, clazz);
        result = HttpAdapter.fetch(url, request.map());
    }

    public void verify(Map<String, String> expected, Class<? extends AbstractResponse> clazz) {
        verify(expected, clazz, null);
    }

    public void verify(Map<String, String> expected, Class<? extends AbstractResponse> clazz,
                       Equalizer<String> equalizer) {
        AbstractResponse actual = Mapper.fromJson(result, clazz);
        Differ.diff(expected, actual, equalizer);
    }
}
