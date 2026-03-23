import java.time.LocalDateTime;

public class ParkingTicket {
    private Vehicle vehicle;
    private LocalDateTime inTime;
    private int gateId;
    private ParkingSlot assignedSlot;

    public ParkingTicket(Vehicle vehicle, int gateId, ParkingSlot assignedSlot) {
        this.vehicle = vehicle;
        this.gateId = gateId;
        this.assignedSlot = assignedSlot;
        this.inTime = LocalDateTime.now();
    }

    public LocalDateTime getInTime() { return inTime; }
    public ParkingSlot getAssignedSlot() { return assignedSlot; }
}