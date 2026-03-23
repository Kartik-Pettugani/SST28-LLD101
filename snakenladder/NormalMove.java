package snakenladder;

class NormalMove implements IMakeMove {
    public int makeMove(Player player, int diceValue) {
        return player.position + diceValue;
    }
}
