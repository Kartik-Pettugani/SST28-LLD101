package elevator;

import java.util.Objects;

public final class CarPanel {
    private final ElevatorSystem system;
    private final Elevator elevator;

    public CarPanel(ElevatorSystem system, Elevator elevator) {
        this.system = Objects.requireNonNull(system, "system");
        this.elevator = Objects.requireNonNull(elevator, "elevator");
    }

    public void pressFloor(int destinationFloor) {
        system.selectDestination(elevator, destinationFloor);
    }
}
