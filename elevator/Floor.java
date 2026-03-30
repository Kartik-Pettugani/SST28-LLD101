package elevator;

public final class Floor {
    private final int number;
    private boolean underMaintenance;

    public Floor(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }
}
