package elevator;

public interface JobSchedulingStrategy {
    /**
     * @return next floor to stop at, or null if none
     */
    Integer nextStop(Elevator elevator);
}
