package elevator;

public final class WeightSensor {
    private double currentWeightKg;

    public double getCurrentWeightKg() {
        return currentWeightKg;
    }

    public void setCurrentWeightKg(double currentWeightKg) {
        if (currentWeightKg < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.currentWeightKg = currentWeightKg;
    }
}
