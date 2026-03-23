package parkinglot;

import java.util.HashMap;
import java.util.Map;

class ParkingLot {
    Floor[] floors;
    Gate[] gates;

    private final NearestSlotAllocationStrategy allocationStrategy;
    private final Map<String, SlotKey> activeTickets;

    ParkingLot(Floor[] floors, Gate[] gates) {
        this.floors = floors;
        this.gates = gates;
        this.allocationStrategy = new NearestSlotAllocationStrategy();
        this.allocationStrategy.seed(floors);
        this.activeTickets = new HashMap<>();
    }

    Ticket parkVehicle(Gate gate, Vehicle v) {
        if (v == null) {
            System.out.println("Invalid vehicle");
            return null;
        }

        SlotType requiredType = requiredSlotType(v.type);
        if (requiredType == null) {
            System.out.println("Invalid vehicle type");
            return null;
        }

        // Nearest slot is the lowest (floorId, slotId) for that type.
        SlotKey candidate;
        while (true) {
            candidate = allocationStrategy.pollForType(requiredType);
            if (candidate == null) {
                System.out.println("No slot available");
                return null;
            }

            Slot slot = getSlot(candidate);
            if (slot != null && !slot.isOccupied && slot.type == requiredType) {
                slot.park(v);
                String ticketId = generateTicketId(gate, v, candidate);
                Ticket ticket = new Ticket(ticketId, v, candidate.floorId, candidate.slotId);
                activeTickets.put(ticketId, candidate);

                System.out.println("Parked at Floor " + candidate.floorId + " Slot " + candidate.slotId);
                System.out.println("Ticket: " + ticketId);
                return ticket;
            }
            // If candidate is stale, keep polling.
        }
    }

    Bill exitVehicle(Ticket ticket) {
        if (ticket == null || ticket.ticketId == null) {
            System.out.println("Invalid ticket");
            return null;
        }

        SlotKey key = activeTickets.remove(ticket.ticketId);
        if (key == null) {
            System.out.println("Ticket not found or already used");
            return null;
        }

        Slot slot = getSlot(key);
        if (slot == null || !slot.isOccupied) {
            System.out.println("Slot already free");
            return null;
        }

        SlotType slotType = slot.type;
        slot.free();
        allocationStrategy.addFreeSlot(slotType, new SlotKey(key.floorId, key.slotId));

        System.out.println("Vehicle exited");

        Bill bill = new Bill(ticket, slotType);
        System.out.println("Amount: " + bill.amount);
        return bill;
    }

    void showStatus() {
        if (floors == null) {
            return;
        }

        for (int i = 0; i < floors.length; i++) {
            Floor f = floors[i];
            if (f == null || f.slots == null) {
                continue;
            }

            System.out.println("Floor " + f.id);

            for (int j = 0; j < f.slots.length; j++) {
                Slot s = f.slots[j];
                if (s == null) {
                    continue;
                }

                System.out.println(
                        "Slot " + s.id +
                                " Type: " + s.type +
                                " Occupied: " + s.isOccupied +
                                (s.isOccupied ? " Vehicle: " + s.vehicle.number : "")
                );
            }
        }
    }

    void showAvailability() {
        if (floors == null) {
            return;
        }

        for (int i = 0; i < floors.length; i++) {
            Floor f = floors[i];
            if (f == null || f.slots == null) {
                continue;
            }

            int freeSmall = 0;
            int freeMedium = 0;
            int freeLarge = 0;

            for (int j = 0; j < f.slots.length; j++) {
                Slot s = f.slots[j];
                if (s == null || s.isOccupied) {
                    continue;
                }

                if (s.type == SlotType.SMALL) freeSmall++;
                else if (s.type == SlotType.MEDIUM) freeMedium++;
                else freeLarge++;
            }

            System.out.println(
                    "Floor " + f.id +
                            " Available -> SMALL: " + freeSmall +
                            ", MEDIUM: " + freeMedium +
                            ", LARGE: " + freeLarge
            );
        }
    }

    private Slot getSlot(SlotKey key) {
        if (key == null || floors == null) {
            return null;
        }

        for (int i = 0; i < floors.length; i++) {
            Floor f = floors[i];
            if (f == null || f.id != key.floorId || f.slots == null) {
                continue;
            }

            for (int j = 0; j < f.slots.length; j++) {
                if (f.slots[j] != null && f.slots[j].id == key.slotId) {
                    return f.slots[j];
                }
            }
        }

        return null;
    }

    private String generateTicketId(Gate gate, Vehicle v, SlotKey key) {
        int gateId = (gate == null) ? 0 : gate.id;
        return "G" + gateId + "-" + v.number + "-F" + key.floorId + "S" + key.slotId + "-" + System.currentTimeMillis();
    }

    private SlotType requiredSlotType(VehicleType type) {
        if (type == VehicleType.BIKE) return SlotType.SMALL;
        if (type == VehicleType.CAR) return SlotType.MEDIUM;
        if (type == VehicleType.TRUCK) return SlotType.LARGE;
        return null;
    }
}
