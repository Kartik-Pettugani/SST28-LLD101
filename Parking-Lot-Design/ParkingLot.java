import java.util.*;

public class ParkingLot {
    private List<ParkingSlot> allSlots;

    public ParkingLot(List<ParkingSlot> slots) {
        this.allSlots = slots;
    }

    public ParkingSlot findNearestAvailableSlot(int gateId, SlotType type) {
        return allSlots.stream()
                .filter(slot -> slot.isFree() && slot.getType() == type)
                .min(Comparator.comparingInt(slot -> slot.getDistance(gateId)))
                .orElse(null);
    }

    public void showStatus() {
        System.out.println("\n--- Current Availability ---");
        for (SlotType t : SlotType.values()) {
            long count = allSlots.stream()
                .filter(s -> s.isFree() && s.getType() == t)
                .count();
            System.out.println(t + " Slots Free: " + count);
        }
    }
}