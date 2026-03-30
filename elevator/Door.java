package elevator;

public final class Door {
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void open() {
        open = true;
    }

    /**
     * @return true if door closed, false if blocked.
     */
    public boolean close(boolean blocked) {
        if (blocked) {
            return false;
        }
        open = false;
        return true;
    }
}
