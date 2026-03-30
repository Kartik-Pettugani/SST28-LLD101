import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor = 0;
    private Direction currentDirection = Direction.IDLE;
    private ElevatorState state = ElevatorState.IDLE;
    private double currentWeight = 0;
    private final double weightLimit = 750.0;
    private TreeSet<Integer> upJobs = new TreeSet<>();
    private TreeSet<Integer> downJobs = new TreeSet<>(Collections.reverseOrder());

    public Elevator(int id) { this.id = id; }

    public void setWeight(double weight) {
        this.currentWeight = weight;
        if (currentWeight > weightLimit) {
            System.out.println("Alarm! Weight limit exceeded. Doors staying open.");
        }
    }

    public void addJob(int floor) {
        if (floor > currentFloor) upJobs.add(floor);
        else if (floor < currentFloor) downJobs.add(floor);
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }
    public void setState(ElevatorState state) { this.state = state; }
    public Direction getDirection() { return currentDirection; }
    public boolean isOverloaded() { return currentWeight > weightLimit; }
}