package pen;

public class InkBottleRefill implements RefillStrategy {
    @Override
    public void refill() {
        System.out.println("Refilled using ink bottle");
    }
}