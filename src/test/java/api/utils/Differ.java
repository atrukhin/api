package api.utils;

import io.cucumber.datatable.internal.difflib.Delta;
import io.cucumber.datatable.internal.difflib.DiffUtils;
import io.cucumber.datatable.internal.difflib.myers.Equalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Differ {

    private Differ() {
    }

    public static <K, V> void diff(Map<K, V> expected, Mappable<K, V> actual) {
        diff(expected, actual.map());
    }

    public static <K, V> void diff(Map<K, V> expected, Map<K, V> actual) {
        diff(expected, actual, null);
    }

    public static <K, V> void diff(Map<K, V> expected, Mappable<K, V> actual, Equalizer<V> equalizer) {
        diff(expected, actual.map(), equalizer);
    }

    public static <K, V> void diff(Map<K, V> expected, Map<K, V> actual, Equalizer<V> equalizer) {
        List<Delta<V>> diff = DiffUtils.diff(
                new ArrayList<>(expected.values()),
                expected.keySet().stream().map(actual::get).collect(Collectors.toList()),
                equalizer
        ).getDeltas();

        if (!diff.isEmpty()) {
            throw new AssertionError(diff);
        }
    }
}
