package pen;

public class Main {
    public static void main(String[] args) {

        Pen pen1 = new BallPointPen(
                "Blue",
                50,
                new ClickWriting(),
                new CartridgeRefill()
        );

        pen1.write("Hello World");
        pen1.refill();
        pen1.write("After refill");


        System.out.println("-----");

        Pen pen2 = new FountainPen(
                "Black",
                30,
                new CapWriting(),
                new InkBottleRefill()
        );

        pen2.write("Fountain Pen Writing");
        pen2.refill();
    }
}
