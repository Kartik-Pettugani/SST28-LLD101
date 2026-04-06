import java.util.*;

public class CacheNode<K, V> {
    private final int capacity;
    private final Map<K, V> storage;
    private final IEvictionPolicy<K> evictionPolicy;

    public CacheNode(int capacity, IEvictionPolicy<K> evictionPolicy) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {
        if (!storage.containsKey(key)) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public void put(K key, V value) {
        if (!storage.containsKey(key) && storage.size() >= capacity) {
            K keyToEvict = evictionPolicy.evictKey();
            storage.remove(keyToEvict);
        }
        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}