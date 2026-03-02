package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static volatile MetricsRegistry INSTANCE;
    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        // Blocks reflection from creating a second instance after initialization.
        if (INSTANCE != null) {
            throw new IllegalStateException("MetricsRegistry is a singleton; use getInstance()");
        }
    }

    // Lazy init + thread-safe (double-checked locking)
    public static MetricsRegistry getInstance() {
        MetricsRegistry local = INSTANCE;
        if (local == null) {
            synchronized (MetricsRegistry.class) {
                local = INSTANCE;
                if (local == null) {
                    local = new MetricsRegistry();
                    INSTANCE = local;
                }
            }
        }
        return local;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    @Serial
    private Object readResolve() {
        return getInstance();
    }
}
