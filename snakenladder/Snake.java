package snakenladder;

class Snake implements IEntity {
    int start;
    int end;

    Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int apply(int position) {
        if (position == start) {
            System.out.println("Snake bite!");
            return end;
        }
        return position;
    }
}
