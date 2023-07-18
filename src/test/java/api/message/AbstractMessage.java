package api.message;

import api.utils.DuplicateAwareHashMap;
import api.utils.Mappable;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractMessage implements Mappable<String, String> {
    protected final Map<String, Function<AbstractMessage, String>> params = new DuplicateAwareHashMap<>();

    @Override
    public final Map<String, String> map() {
        return params.entrySet().stream()
                .filter(e -> e.getValue() != null && e.getValue().apply(this) != null)
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, e -> e.getValue().apply(this)));
    }
}
