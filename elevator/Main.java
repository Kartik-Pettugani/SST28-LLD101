package elevator;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
        Building building = new Building(0, 10);

        Elevator e1 = new Elevator(
                1,
                0,
                750,
                new Door(),
                new WeightSensor(),
                new BuzzerAlarm(),
                new ScanJobSchedulingStrategy()
        );

        Elevator e2 = new Elevator(
                2,
                7,
                750,
                new Door(),
                new WeightSensor(),
                new BuzzerAlarm(),
                new ScanJobSchedulingStrategy()
        );

        ElevatorSystem system = new ElevatorSystem(
                building,
                List.of(e1, e2),
                new NearestElevatorSelectionStrategy()
        );

        // Maintenance requirements
        system.setElevatorMaintenance(2, true);
        system.setFloorMaintenance(3, true);

        FloorPanel floor5 = new FloorPanel(5, system);

        System.out.println("Requesting elevator from floor 5 (UP)");
        Elevator assigned = system.requestElevator(5, Direction.UP);
        System.out.println("Assigned elevator: " + assigned.getId());

        // Simulate steps until elevator reaches pickup
        while (assigned.getCurrentFloor() != 5) {
            assigned.step();
            System.out.println(assigned);
        }

        // Passenger enters, tries to close doors while overweight
        assigned.setCurrentWeightKg(800);
        boolean closed = assigned.tryCloseDoors();
        System.out.println("Door closed? " + closed);

        // Reduce weight and close
        assigned.setCurrentWeightKg(500);
        closed = assigned.tryCloseDoors();
        System.out.println("Door closed after reducing weight? " + closed);

        // Inside cabin: choose destination floors (same panel)
        CarPanel carPanel = new CarPanel(system, assigned);
        carPanel.pressFloor(9);

        // NOTE: floor 3 is under maintenance; selecting it should fail
        try {
            carPanel.pressFloor(3);
        } catch (Exception ex) {
            System.out.println("Expected: cannot select floor 3 -> " + ex.getMessage());
        }

        // Run until all stops served
        while (assigned.pendingStopsCount() > 0 || assigned.getState() != ElevatorState.IDLE) {
            assigned.step();
            System.out.println(assigned);
        }

        System.out.println("Done");
    }
}
