import java.util.*;

public class Database<K, V> {
    private final Map<K, V> dbStorage = new HashMap<>();

    public V fetch(K key) {
        return dbStorage.get(key);
    }

    public void save(K key, V value) {
        dbStorage.put(key, value);
    }
}