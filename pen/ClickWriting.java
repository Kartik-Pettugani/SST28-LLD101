package pen;

public class ClickWriting implements WritingStrategy {
    @Override
    public void write(String text) {
        System.out.println("Click Pen writing: " + text);
    }
}