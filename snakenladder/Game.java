package snakenladder;

class Game {
    Player[] players;
    int current;
    Board board;
    Dice dice;
    IMakeMove strategy;

    Game(Player[] players, Board board, IMakeMove strategy) {
        this.players = players;
        this.board = board;
        this.strategy = strategy;
        this.dice = new Dice();
        this.current = 0;
    }

    void start() {
        while (true) {

            Player p = players[current];

            int roll = dice.roll();
            System.out.println(p.name + " rolled " + roll);

            int newPos = strategy.makeMove(p, roll);

            if (newPos > board.size * board.size) {
                nextTurn();
                continue;
            }

            newPos = board.applyEntities(newPos);
            p.position = newPos;

            System.out.println(p.name + " at " + newPos);

            if (newPos == board.size * board.size) {
                System.out.println(p.name + " wins!");
                break;
            }

            nextTurn();
        }
    }

    void nextTurn() {
        current = (current + 1) % players.length;
    }
}
