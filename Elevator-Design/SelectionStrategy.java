import java.util.List;

public interface SelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, ExternalRequest request);
}