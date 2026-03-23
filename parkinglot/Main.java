package parkinglot;

public class Main {
    public static void main(String[] args) {

        Slot[] slots1 = {
                new Slot(1, SlotType.SMALL),
                new Slot(2, SlotType.MEDIUM),
                new Slot(3, SlotType.LARGE)
        };

        Slot[] slots2 = {
                new Slot(1, SlotType.SMALL),
                new Slot(2, SlotType.MEDIUM),
                new Slot(3, SlotType.LARGE)
        };

        Floor f1 = new Floor(1, slots1);
        Floor f2 = new Floor(2, slots2);

        Gate g1 = new Gate(1);
        Gate g2 = new Gate(2);

        ParkingLot lot = new ParkingLot(new Floor[]{f1, f2}, new Gate[]{g1, g2});

        Vehicle v1 = new Vehicle("AP01", VehicleType.CAR);

        Ticket t = lot.parkVehicle(g1, v1);

        lot.showAvailability();
        lot.showStatus();

        try {
            Thread.sleep(2000);
        } catch (Exception ignored) {
        }

        lot.exitVehicle(t);
        lot.showAvailability();
    }
}
