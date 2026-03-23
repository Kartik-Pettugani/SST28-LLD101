package pen;

public class FountainPen extends Pen {

    public FountainPen(
            String color,
            int inkLevel,
            WritingStrategy writingStrategy,
            RefillStrategy refillStrategy
    ) {

        super(color, inkLevel, writingStrategy, refillStrategy);
    }
}