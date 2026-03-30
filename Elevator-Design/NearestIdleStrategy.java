import java.util.List;

public class NearestIdleStrategy implements SelectionStrategy {
    @Override
    public Elevator selectElevator(List<Elevator> elevators, ExternalRequest request) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getState() != ElevatorState.UNDER_MAINTENANCE) {
                int distance = Math.abs(e.getCurrentFloor() - request.getSourceFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    best = e;
                }
            }
        }
        return best;
    }
}