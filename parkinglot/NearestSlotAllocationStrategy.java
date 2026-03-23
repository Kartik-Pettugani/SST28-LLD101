package parkinglot;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.PriorityQueue;

class NearestSlotAllocationStrategy implements SlotAllocationStrategy {

    private final EnumMap<SlotType, PriorityQueue<SlotKey>> freeSlots;

    NearestSlotAllocationStrategy() {
        freeSlots = new EnumMap<>(SlotType.class);

        Comparator<SlotKey> comparator = (a, b) -> {
            if (a.floorId != b.floorId) {
                return Integer.compare(a.floorId, b.floorId);
            }
            return Integer.compare(a.slotId, b.slotId);
        };

        freeSlots.put(SlotType.SMALL, new PriorityQueue<>(comparator));
        freeSlots.put(SlotType.MEDIUM, new PriorityQueue<>(comparator));
        freeSlots.put(SlotType.LARGE, new PriorityQueue<>(comparator));
    }

    void seed(Floor[] floors) {
        if (floors == null) {
            return;
        }

        for (int i = 0; i < floors.length; i++) {
            Floor floor = floors[i];
            if (floor == null || floor.slots == null) {
                continue;
            }

            for (int j = 0; j < floor.slots.length; j++) {
                Slot slot = floor.slots[j];
                if (slot == null) {
                    continue;
                }

                if (!slot.isOccupied) {
                    freeSlots.get(slot.type).offer(new SlotKey(floor.id, slot.id));
                }
            }
        }
    }

    public SlotKey allocate(Vehicle vehicle) {
        if (vehicle == null || vehicle.type == null) {
            return null;
        }

        SlotType required = requiredSlotType(vehicle.type);
        if (required == null) {
            return null;
        }

        return freeSlots.get(required).peek();
    }

    public void onParked(SlotKey key) {
        if (key == null) {
            return;
        }
        // Actual removal happens in ParkingLot after verifying the slot.
    }

    public void onFreed(SlotKey key) {
        if (key == null) {
            return;
        }
        // Re-adding happens in ParkingLot (needs slot type).
    }

    SlotKey pollForType(SlotType type) {
        return freeSlots.get(type).poll();
    }

    void addFreeSlot(SlotType type, SlotKey key) {
        freeSlots.get(type).offer(key);
    }

    private SlotType requiredSlotType(VehicleType vehicleType) {
        if (vehicleType == VehicleType.BIKE) return SlotType.SMALL;
        if (vehicleType == VehicleType.CAR) return SlotType.MEDIUM;
        if (vehicleType == VehicleType.TRUCK) return SlotType.LARGE;
        return null;
    }
}
