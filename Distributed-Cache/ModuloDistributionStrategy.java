public class ModuloDistributionStrategy implements IDistributionStrategy {
    @Override
    public int getTargetNodeIndex(String key, int numberOfNodes) {
        return Math.abs(key.hashCode()) % numberOfNodes;
    }
}