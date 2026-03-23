import java.util.Map;

public class ParkingSlot {
    private int id;
    private int floor;
    private SlotType type;
    private boolean isFree;
    private Map<Integer, Integer> gateDistances;

    public ParkingSlot(int id, int floor, SlotType type, Map<Integer, Integer> gateDistances) {
        this.id = id;
        this.floor = floor;
        this.type = type;
        this.isFree = true;
        this.gateDistances = gateDistances;
    }

    public int getDistance(int gateId) {
        return gateDistances.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public boolean isFree() { return isFree; }
    public void setFree(boolean free) { isFree = free; }
    public SlotType getType() { return type; }
    public int getId() { return id; }
}