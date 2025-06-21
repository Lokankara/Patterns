package patterns.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeastRecentlyUsedCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
