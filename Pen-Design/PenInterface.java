public interface PenInterface {
    void start();
    void write(String text);
    void refill();
    void close();
    String getColor();
}