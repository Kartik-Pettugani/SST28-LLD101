public class Main {
    public static void main(String[] args) {
        Database<String, String> realDb = new Database<>();
        realDb.save("user_1", "Alice");
        realDb.save("user_2", "Bob");

        DistributedCache<String, String> cache = new DistributedCache<>(
            3, 
            2, 
            new ModuloDistributionStrategy(), 
            realDb
        );

        System.out.println("Result 1: " + cache.get("user_1"));
        
        cache.put("user_3", "Charlie");
        cache.put("user_4", "David");
        cache.put("user_5", "Eve");

        System.out.println("Result 2: " + cache.get("user_3"));
        System.out.println("Result 3: " + cache.get("user_1"));
    }
}