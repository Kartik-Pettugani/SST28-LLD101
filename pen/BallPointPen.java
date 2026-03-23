package pen;

public class BallPointPen extends Pen {

    public BallPointPen(
            String color,
            int inkLevel,
            WritingStrategy writingStrategy,
            RefillStrategy refillStrategy
    ) {

        super(color, inkLevel, writingStrategy, refillStrategy);
    }
}