package api.utils;

import java.util.Map;

public interface Mappable<K, V> {
    Map<K, V> map();
}
