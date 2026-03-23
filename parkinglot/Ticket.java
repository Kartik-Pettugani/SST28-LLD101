package parkinglot;

class Ticket {
    String ticketId;
    Vehicle vehicle;
    long entryTime;
    int floorId;
    int slotId;

    Ticket(String ticketId, Vehicle vehicle, int floorId, int slotId) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.floorId = floorId;
        this.slotId = slotId;
        this.entryTime = System.currentTimeMillis();
    }
}
