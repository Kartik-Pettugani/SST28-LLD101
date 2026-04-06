public interface IDistributionStrategy {
    int getTargetNodeIndex(String key, int numberOfNodes);
}