package pen;

public class CartridgeRefill implements RefillStrategy {
    @Override
    public void refill() {
        System.out.println("Refilled using cartridge");
    }
}