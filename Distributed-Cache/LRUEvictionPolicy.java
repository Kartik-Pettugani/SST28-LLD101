import java.util.LinkedHashSet;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K> {
    private final LinkedHashSet<K> keyOrder;

    public LRUEvictionPolicy() {
        this.keyOrder = new LinkedHashSet<>();
    }

    @Override
    public void keyAccessed(K key) {
        keyOrder.remove(key);
        keyOrder.add(key);
    }

    @Override
    public K evictKey() {
        K oldestKey = keyOrder.iterator().next();
        keyOrder.remove(oldestKey);
        return oldestKey;
    }
}