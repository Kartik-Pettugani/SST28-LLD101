package snakenladder;

class Board {
    int size;
    IEntity[] entities;

    Board(int size, IEntity[] entities) {
        this.size = size;
        this.entities = entities;
    }

    int applyEntities(int position) {
        for (int i = 0; i < entities.length; i++) {
            position = entities[i].apply(position);
        }
        return position;
    }
}
