package elevator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Building {
    private final Map<Integer, Floor> floorsByNumber;

    public Building(int minFloor, int maxFloor) {
        if (minFloor > maxFloor) {
            throw new IllegalArgumentException("minFloor must be <= maxFloor");
        }
        Map<Integer, Floor> floors = new HashMap<>();
        for (int f = minFloor; f <= maxFloor; f++) {
            floors.put(f, new Floor(f));
        }
        this.floorsByNumber = floors;
    }

    public Floor getFloorOrThrow(int floorNumber) {
        Floor floor = floorsByNumber.get(floorNumber);
        if (floor == null) {
            throw new IllegalArgumentException("Unknown floor: " + floorNumber);
        }
        return floor;
    }

    public Map<Integer, Floor> getFloorsByNumber() {
        return Collections.unmodifiableMap(floorsByNumber);
    }
}
