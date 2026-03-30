package elevator;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator select(List<Elevator> elevators, HallCallRequest request);
}
