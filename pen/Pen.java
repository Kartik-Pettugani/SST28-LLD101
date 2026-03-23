package pen;

import java.util.Objects;

public abstract class Pen {
    private static final int DEFAULT_MAX_INK_LEVEL = 100;

    private final String color;
    private final int maxInkLevel;
    private int inkLevel;

    private final WritingStrategy writingStrategy;
    private final RefillStrategy refillStrategy;

    protected Pen(
            String color,
            int inkLevel,
            WritingStrategy writingStrategy,
            RefillStrategy refillStrategy
    ) {
        this.color = Objects.requireNonNull(color, "color");
        this.writingStrategy = Objects.requireNonNull(writingStrategy, "writingStrategy");
        this.refillStrategy = Objects.requireNonNull(refillStrategy, "refillStrategy");

        this.maxInkLevel = DEFAULT_MAX_INK_LEVEL;
        this.inkLevel = clampInkLevel(inkLevel);
    }

    public String getColor() {
        return color;
    }

    public int getInkLevel() {
        return inkLevel;
    }

    public int getMaxInkLevel() {
        return maxInkLevel;
    }

    public void write(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        if (inkLevel <= 0) {
            System.out.println("No ink");
            return;
        }

        if (text.length() <= inkLevel) {
            writingStrategy.write(text);
            inkLevel -= text.length();
            return;
        }

        // Not enough ink to write everything; write what we can.
        String partial = text.substring(0, inkLevel);
        writingStrategy.write(partial);
        inkLevel = 0;
    }

    public void refill() {
        refillStrategy.refill();
        inkLevel = maxInkLevel;
    }

    private int clampInkLevel(int level) {
        return Math.max(0, Math.min(level, maxInkLevel));
    }
}