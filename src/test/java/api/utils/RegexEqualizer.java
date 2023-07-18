package api.utils;

import io.cucumber.datatable.internal.difflib.myers.Equalizer;

public final class RegexEqualizer implements Equalizer<String> {

    @Override
    public boolean equals(String expected, String actual) {
        if (expected == null || actual == null) {
            return expected == null && actual == null;
        }
        return actual.matches(expected);
    }
}
