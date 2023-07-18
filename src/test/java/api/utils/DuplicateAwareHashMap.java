package api.utils;

import java.util.HashMap;

public final class DuplicateAwareHashMap<K, V> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        if (containsKey(key)) {
            throw new IllegalStateException(String.format("Duplicate key: %s", key));
        }
        return super.put(key, value);
    }
}
