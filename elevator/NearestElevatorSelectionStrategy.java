package elevator;

import java.util.Comparator;
import java.util.List;

/**
 * Minimal selection:
 * - ignore elevators under maintenance
 * - prefer IDLE, otherwise pick nearest by distance.
 */
public final class NearestElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    @Override
    public Elevator select(List<Elevator> elevators, HallCallRequest request) {
        return elevators.stream()
                .filter(e -> e.getState() != ElevatorState.UNDER_MAINTENANCE)
                .min(Comparator
                        .comparing((Elevator e) -> e.getState() != ElevatorState.IDLE)
                        .thenComparingInt(e -> Math.abs(e.getCurrentFloor() - request.floor())))
                .orElseThrow(() -> new IllegalStateException("No elevators available"));
    }
}
