package snakenladder;

class Dice {
    int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}
