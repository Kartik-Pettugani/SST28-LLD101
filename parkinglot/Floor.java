package parkinglot;

class Floor {
    int id;
    Slot[] slots;

    Floor(int id, Slot[] slots) {
        this.id = id;
        this.slots = slots;
    }

    Slot getAvailableSlot(Vehicle v) {
        if (slots == null) {
            return null;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && !slots[i].isOccupied && slots[i].canFit(v)) {
                return slots[i];
            }
        }
        return null;
    }
}
