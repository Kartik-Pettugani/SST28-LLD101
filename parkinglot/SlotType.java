package parkinglot;

enum SlotType {
    SMALL,
    MEDIUM,
    LARGE;

    boolean canFit(VehicleType vehicleType) {
        if (vehicleType == null) {
            return false;
        }

        // As per requirement: SMALL -> BIKE, MEDIUM -> CAR, LARGE -> TRUCK
        return (this == SMALL && vehicleType == VehicleType.BIKE)
                || (this == MEDIUM && vehicleType == VehicleType.CAR)
                || (this == LARGE && vehicleType == VehicleType.TRUCK);
    }

    int ratePerSecond() {
        if (this == SMALL) return 10;
        if (this == MEDIUM) return 20;
        return 30;
    }
}
