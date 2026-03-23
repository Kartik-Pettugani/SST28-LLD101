import java.util.*;

public class Board {
    private final int n;
    private final int maxCell;
    private final Map<Integer, Jumper> jumpers = new HashMap<>();

    public Board(int n, String difficulty) {
        this.n = n;
        this.maxCell = n * n;
        setupJumpers(difficulty);
    }

    private void setupJumpers(String difficulty) {
        Random rand = new Random();
        int count = 0;

        while (count < n) {
            int start = rand.nextInt(maxCell - n) + 1;
            int end = start + (difficulty.equalsIgnoreCase("easy") ? (rand.nextInt(2*n)+n) : rand.nextInt(n));
            
            if (end < maxCell && !jumpers.containsKey(start)) {
                jumpers.put(start, new Jumper(start, end));
                count++;
            }
        }

        count = 0;
        while (count < n) {
            int start = rand.nextInt(maxCell - 2) + 2;
            int end = start - (difficulty.equalsIgnoreCase("hard") ? (rand.nextInt(2*n)+n) : rand.nextInt(n));
            if (end > 0 && !jumpers.containsKey(start) && !isEndPosition(start)) {
                jumpers.put(start, new Jumper(start, end));
                count++;
            }
        }
    }

    private boolean isEndPosition(int pos) {
        for (Jumper j : jumpers.values()) {
            if (j.getEnd() == pos) return true;
        }
        return false;
    }

    public int resolveJump(int pos) {
        if (jumpers.containsKey(pos)) {
            return jumpers.get(pos).getEnd();
        }
        return pos;
    }

    public int getMaxCell() { return maxCell; }
}