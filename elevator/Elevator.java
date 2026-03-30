package elevator;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public final class Elevator {
    private final int id;
    private final double maxWeightKg;
    private final Door door;
    private final WeightSensor weightSensor;
    private final Alarm alarm;
    private final JobSchedulingStrategy schedulingStrategy;

    private int currentFloor;
    private ElevatorState state;
    private Direction travelDirection;
    private final NavigableSet<Integer> pendingStops;

    public Elevator(int id,
                   int initialFloor,
                   double maxWeightKg,
                   Door door,
                   WeightSensor weightSensor,
                   Alarm alarm,
                   JobSchedulingStrategy schedulingStrategy) {
        this.id = id;
        this.currentFloor = initialFloor;
        this.maxWeightKg = maxWeightKg;
        this.door = Objects.requireNonNull(door, "door");
        this.weightSensor = Objects.requireNonNull(weightSensor, "weightSensor");
        this.alarm = Objects.requireNonNull(alarm, "alarm");
        this.schedulingStrategy = Objects.requireNonNull(schedulingStrategy, "schedulingStrategy");
        this.state = ElevatorState.IDLE;
        this.pendingStops = new TreeSet<>();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public Direction getTravelDirection() {
        return travelDirection;
    }

    void setTravelDirection(Direction direction) {
        this.travelDirection = direction;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        if (underMaintenance) {
            state = ElevatorState.UNDER_MAINTENANCE;
            pendingStops.clear();
            travelDirection = null;
        } else {
            state = ElevatorState.IDLE;
        }
    }

    public void setCurrentWeightKg(double weightKg) {
        weightSensor.setCurrentWeightKg(weightKg);
    }

    public void addStop(int floor) {
        if (state == ElevatorState.UNDER_MAINTENANCE) {
            return;
        }
        pendingStops.add(floor);
        if (state == ElevatorState.IDLE) {
            Integer next = schedulingStrategy.nextStop(this);
            updateMotionState(next);
        }
    }

    public int pendingStopsCount() {
        return pendingStops.size();
    }

    Integer nextHigherOrEqualStop(int floor) {
        return pendingStops.ceiling(floor);
    }

    Integer nextLowerOrEqualStop(int floor) {
        return pendingStops.floor(floor);
    }

    /**
     * Move the elevator one step (one floor) towards the next scheduled stop.
     * Call repeatedly to simulate elevator operation.
     */
    public void step() {
        if (state == ElevatorState.UNDER_MAINTENANCE) {
            return;
        }

        if (pendingStops.isEmpty()) {
            state = ElevatorState.IDLE;
            travelDirection = null;
            return;
        }

        Integer target = schedulingStrategy.nextStop(this);
        if (target == null) {
            state = ElevatorState.IDLE;
            travelDirection = null;
            return;
        }

        updateMotionState(target);

        if (currentFloor < target) {
            currentFloor++;
        } else if (currentFloor > target) {
            currentFloor--;
        }

        if (currentFloor == target) {
            pendingStops.remove(target);
            arriveAndOpenDoors();
        }

        if (pendingStops.isEmpty()) {
            state = ElevatorState.IDLE;
            travelDirection = null;
        }
    }

    private void updateMotionState(Integer targetFloor) {
        if (targetFloor == null) {
            state = ElevatorState.IDLE;
            return;
        }
        if (targetFloor > currentFloor) {
            state = ElevatorState.MOVING_UP;
            travelDirection = Direction.UP;
        } else if (targetFloor < currentFloor) {
            state = ElevatorState.MOVING_DOWN;
            travelDirection = Direction.DOWN;
        } else {
            // already at target
            if (state == ElevatorState.IDLE) {
                travelDirection = null;
            }
        }
    }

    private void arriveAndOpenDoors() {
        door.open();
    }

    /**
     * Requirement: when weight limit breached, door should not close + alarm should play.
     */
    public boolean tryCloseDoors() {
        boolean overweight = weightSensor.getCurrentWeightKg() > maxWeightKg;
        boolean closed = door.close(overweight);
        if (!closed && overweight) {
            alarm.play("Overweight: " + weightSensor.getCurrentWeightKg() + "kg > limit " + maxWeightKg + "kg");
        }
        return closed;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", floor=" + currentFloor +
                ", state=" + state +
                ", pendingStops=" + pendingStops +
                ", doorOpen=" + door.isOpen() +
                '}';
    }
}
