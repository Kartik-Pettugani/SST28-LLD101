import java.util.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> distancesForSlot1 = new HashMap<>();
        distancesForSlot1.put(1, 10);
        distancesForSlot1.put(2, 50);

        Map<Integer, Integer> distancesForSlot2 = new HashMap<>();
        distancesForSlot2.put(1, 40);
        distancesForSlot2.put(2, 15);
        ParkingSlot s1 = new ParkingSlot(101, 1, SlotType.MEDIUM, distancesForSlot1);
        ParkingSlot s2 = new ParkingSlot(102, 1, SlotType.MEDIUM, distancesForSlot2);

        ParkingLot lot = new ParkingLot(Arrays.asList(s1, s2));
        Vehicle myCar = new Vehicle("KA-01-HH-1234", "Black", "Tesla");
        ParkingSlot nearest = lot.findNearestAvailableSlot(1, SlotType.MEDIUM);

        if (nearest != null) {
            nearest.setFree(false);
            ParkingTicket ticket = new ParkingTicket(myCar, 1, nearest);
            System.out.println("Allocated Slot " + nearest.getId() + " (Nearest to Gate 1)");

            double amount = BillGenerator.generateBill(ticket, LocalDateTime.now().plusHours(2));
            System.out.printf("Bill Amount: ₹%.2f%n", amount);
            
            nearest.setFree(true);
            System.out.println("Slot " + nearest.getId() + " is now free.");
        }

        lot.showStatus();
    }
}