package parkinglot;

class Slot {
    int id;
    SlotType type;
    boolean isOccupied;
    Vehicle vehicle;

    Slot(int id, SlotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    boolean canFit(Vehicle v) {
        return v != null && type != null && type.canFit(v.type);
    }

    void park(Vehicle v) {
        if (isOccupied) {
            throw new IllegalStateException("Slot already occupied");
        }
        if (!canFit(v)) {
            throw new IllegalArgumentException("Vehicle cannot fit in this slot");
        }
        this.vehicle = v;
        this.isOccupied = true;
    }

    void free() {
        this.vehicle = null;
        this.isOccupied = false;
    }
}
