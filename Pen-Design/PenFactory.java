public class PenFactory {
    public static PenInterface createPen(String type, String color, boolean hasCap) {
        if (type.equalsIgnoreCase("ball")) {
            return new BallPen(color, hasCap);
        } else if (type.equalsIgnoreCase("gel")) {
            return new GelPen(color, hasCap);
        } else {
            throw new IllegalArgumentException("Unknown pen type: " + type);
        }
    }
}
