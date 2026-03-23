package parkinglot;

class Bill {
    Ticket ticket;
    long exitTime;
    int amount;

    Bill(Ticket ticket, SlotType slotType) {
        this.ticket = ticket;
        this.exitTime = System.currentTimeMillis();
        calculate(slotType);
    }

    void calculate(SlotType slotType) {
        long durationSeconds = (exitTime - ticket.entryTime) / 1000;

        int rate = slotType.ratePerSecond();
        amount = (int) durationSeconds * rate;
    }
}
