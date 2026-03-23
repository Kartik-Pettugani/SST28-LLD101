public class Main {
    public static void main(String[] args){
        PenInterface ballPen = PenFactory.createPen("ball", "blue", true);
        ballPen.start();
        ballPen.write("Hello, World!");
        ballPen.refill();
        ballPen.close();

        PenInterface gelPen = PenFactory.createPen("gel", "red", false);
        gelPen.start();
        gelPen.write("This is a gel pen.");
        gelPen.refill();
        gelPen.close();
    }
}
