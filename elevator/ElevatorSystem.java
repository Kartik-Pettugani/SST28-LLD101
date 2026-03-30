package elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ElevatorSystem {
    private final Building building;
    private final List<Elevator> elevators;
    private final ElevatorSelectionStrategy selectionStrategy;

    public ElevatorSystem(Building building, List<Elevator> elevators, ElevatorSelectionStrategy selectionStrategy) {
        this.building = Objects.requireNonNull(building, "building");
        this.elevators = new ArrayList<>(Objects.requireNonNull(elevators, "elevators"));
        this.selectionStrategy = Objects.requireNonNull(selectionStrategy, "selectionStrategy");
    }

    public Building getBuilding() {
        return building;
    }

    public List<Elevator> getElevators() {
        return Collections.unmodifiableList(elevators);
    }

    /**
     * Hall call: user presses up/down on a floor.
     * Requirement: assign exactly one elevator for one request.
     */
    public Elevator requestElevator(int floorNumber, Direction direction) {
        Floor floor = building.getFloorOrThrow(floorNumber);
        if (floor.isUnderMaintenance()) {
            throw new IllegalStateException("Floor under maintenance: " + floorNumber);
        }

        HallCallRequest request = HallCallRequest.create(floorNumber, direction);
        Elevator selected = selectionStrategy.select(elevators, request);

        // pickup stop
        selected.addStop(floorNumber);
        return selected;
    }

    /**
     * Car panel: once inside the elevator, user selects a destination floor.
     */
    public void selectDestination(Elevator elevator, int destinationFloor) {
        Floor floor = building.getFloorOrThrow(destinationFloor);
        if (floor.isUnderMaintenance()) {
            throw new IllegalStateException("Destination floor under maintenance: " + destinationFloor);
        }
        elevator.addStop(destinationFloor);
    }

    public void setElevatorMaintenance(int elevatorId, boolean underMaintenance) {
        Elevator elevator = elevators.stream()
                .filter(e -> e.getId() == elevatorId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown elevator: " + elevatorId));

        elevator.setUnderMaintenance(underMaintenance);
    }

    public void setFloorMaintenance(int floorNumber, boolean underMaintenance) {
        building.getFloorOrThrow(floorNumber).setUnderMaintenance(underMaintenance);
    }
}
