package elevator;

import java.util.Objects;

public final class FloorPanel {
    private final int floorNumber;
    private final ElevatorSystem system;

    public FloorPanel(int floorNumber, ElevatorSystem system) {
        this.floorNumber = floorNumber;
        this.system = Objects.requireNonNull(system, "system");
    }

    public void pressUp() {
        system.requestElevator(floorNumber, Direction.UP);
    }

    public void pressDown() {
        system.requestElevator(floorNumber, Direction.DOWN);
    }
}
