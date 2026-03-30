import java.util.*;

public class ElevatorController {
    private List<Elevator> elevators;
    private SelectionStrategy selectionStrategy;
    private Set<Integer> maintenanceFloors = new HashSet<>();

    public ElevatorController(int numElevators, SelectionStrategy strategy) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) elevators.add(new Elevator(i));
        this.selectionStrategy = strategy;
    }

    public void setMaintenanceFloor(int floor, boolean isUnderMaintenance) {
        if (isUnderMaintenance) maintenanceFloors.add(floor);
        else maintenanceFloors.remove(floor);
    }

    public void handleExternalRequest(ExternalRequest request) {
        if (maintenanceFloors.contains(request.getSourceFloor())) {
            System.out.println("Floor " + request.getSourceFloor() + " buttons disabled.");
            return;
        }
        
        Elevator selected = selectionStrategy.selectElevator(elevators, request);
        if (selected != null) {
            selected.addJob(request.getSourceFloor());
            System.out.println("Assigned Elevator " + selected.getId() + " to floor " + request.getSourceFloor());
        }
    }

    public void handleInternalRequest(int elevatorId, InternalRequest request) {
        Elevator e = elevators.get(elevatorId);
        if (e.getState() != ElevatorState.UNDER_MAINTENANCE && !e.isOverloaded()) {
            e.addJob(request.getDestinationFloor());
        }
    }
}