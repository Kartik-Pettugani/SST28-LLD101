package snakenladder;

class Mine implements IEntity {
    int position;

    Mine(int position) {
        this.position = position;
    }

    public int apply(int pos) {
        if (pos == position) {
            System.out.println("Hit a mine! Go to start.");
            return 0;
        }
        return pos;
    }
}
