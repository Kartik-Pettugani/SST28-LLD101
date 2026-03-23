package snakenladder;

public class Main {
    public static void main(String[] args) {

        IEntity[] entities = {
                new Snake(14, 7),
                new Ladder(3, 22),
                new Mine(10)
        };

        Board board = new Board(10, entities);

        Player[] players = {
                new Player("Kartik"),
                new Player("Rahul")
        };

        IMakeMove strategy = new SkipOnThreeSix();

        Game game = new Game(players, board, strategy);
        game.start();
    }
}
