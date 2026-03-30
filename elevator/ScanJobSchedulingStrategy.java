package elevator;

/**
 * Minimal SCAN scheduling:
 * - maintain current travel direction
 * - serve stops in that direction first, then reverse if needed
 */
public final class ScanJobSchedulingStrategy implements JobSchedulingStrategy {
    @Override
    public Integer nextStop(Elevator elevator) {
        if (elevator.pendingStopsCount() == 0) {
            return null;
        }

        int current = elevator.getCurrentFloor();
        Direction dir = elevator.getTravelDirection();

        if (dir == null) {
            Integer above = elevator.nextHigherOrEqualStop(current);
            Integer below = elevator.nextLowerOrEqualStop(current);
            if (above == null) {
                elevator.setTravelDirection(Direction.DOWN);
                return below;
            }
            if (below == null) {
                elevator.setTravelDirection(Direction.UP);
                return above;
            }
            if (Math.abs(above - current) <= Math.abs(current - below)) {
                elevator.setTravelDirection(Direction.UP);
                return above;
            }
            elevator.setTravelDirection(Direction.DOWN);
            return below;
        }

        if (dir == Direction.UP) {
            Integer next = elevator.nextHigherOrEqualStop(current);
            if (next != null) {
                return next;
            }
            elevator.setTravelDirection(Direction.DOWN);
            return elevator.nextLowerOrEqualStop(current);
        }

        Integer next = elevator.nextLowerOrEqualStop(current);
        if (next != null) {
            return next;
        }
        elevator.setTravelDirection(Direction.UP);
        return elevator.nextHigherOrEqualStop(current);
    }
}
