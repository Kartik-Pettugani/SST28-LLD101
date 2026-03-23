package parkinglot;

interface SlotAllocationStrategy {
    SlotKey allocate(Vehicle vehicle);
    void onParked(SlotKey key);
    void onFreed(SlotKey key);
}
