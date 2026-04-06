import java.util.ArrayList;
import java.util.List;

public class DistributedCache<K, V> {
    private final List<CacheNode<K, V>> nodes;
    private final IDistributionStrategy distributionStrategy;
    private final Database<K, V> database;

    public DistributedCache(int numNodes, int capacityPerNode, 
                            IDistributionStrategy strategy, Database<K, V> db) {
        this.nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new CacheNode<>(capacityPerNode, new LRUEvictionPolicy<>()));
        }
        this.distributionStrategy = strategy;
        this.database = db;
    }

    public V get(K key) {
        int nodeIndex = distributionStrategy.getTargetNodeIndex(key.toString(), nodes.size());
        CacheNode<K, V> targetNode = nodes.get(nodeIndex);

        V value = targetNode.get(key);

        if (value == null) {
            value = database.fetch(key);
            if (value != null) {
                targetNode.put(key, value);
            }
        }
        return value;
    }

    public void put(K key, V value) {
        int nodeIndex = distributionStrategy.getTargetNodeIndex(key.toString(), nodes.size());
        nodes.get(nodeIndex).put(key, value);
        database.save(key, value);
    }
}