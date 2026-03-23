package pen;

public class CapWriting implements WritingStrategy {
    @Override
    public void write(String text) {
        System.out.println("Cap Pen writing: " + text);
    }
}