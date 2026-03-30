import java.util.List;

public interface SchedulingStrategy {
    List<Integer> determineOrder(Elevator elevator, List<Integer> pendingFloors);
}