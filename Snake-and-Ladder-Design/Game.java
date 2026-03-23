import java.util.*;

public class Game {
    private final Board board;
    private final Dice dice = new Dice();
    private final Queue<Player> players = new LinkedList<>();
    private final List<Player> leaderboard = new ArrayList<>();
    public Game(int n, int x, String difficulty) {
        this.board = new Board(n, difficulty);
        for (int i = 1; i <= x; i++) {
            players.add(new Player("Player " + i));
        }
    }

    public void play() {
        while (players.size() >= 2) {
            Player p = players.poll();
            int roll = dice.roll();
            int currentPos = p.getCurrentPosition();
            int targetPos = currentPos + roll;

            if (targetPos <= board.getMaxCell()) {
                int finalPos = board.resolveJump(targetPos);
                p.setCurrentPosition(finalPos);
                System.out.println(p.getName() + " rolled " + roll + " and moved from " + currentPos + " to " + finalPos);

                if (finalPos == board.getMaxCell()) {
                    System.out.println(p.getName() + " WON!");
                    leaderboard.add(p);
                    continue;
                }
            } else {
                System.out.println(p.getName() + " rolled " + roll + " but needs exactly " + (board.getMaxCell() - currentPos) + " to win.");
            }
            players.add(p);
        }
        System.out.println("Game Finished!");
    }
}