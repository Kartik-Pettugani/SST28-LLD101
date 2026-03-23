package snakenladder;

class SkipOnThreeSix implements IMakeMove {
    int countSix = 0;

    public int makeMove(Player player, int diceValue) {

        if (diceValue == 6) {
            countSix++;
        } else {
            countSix = 0;
        }

        if (countSix == 3) {
            System.out.println("3 consecutive sixes! Skip turn.");
            countSix = 0;
            return player.position;
        }

        return player.position + diceValue;
    }
}
