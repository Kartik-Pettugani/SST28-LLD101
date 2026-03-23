package snakenladder;

class Ladder implements IEntity {
    int start;
    int end;

    Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int apply(int position) {
        if (position == start) {
            System.out.println("Climbed ladder!");
            return end;
        }
        return position;
    }
}
