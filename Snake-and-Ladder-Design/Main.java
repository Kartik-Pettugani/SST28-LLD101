import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Board Size (n): ");
        int n = sc.nextInt();
        System.out.print("Enter Number of Players (x): ");
        int x = sc.nextInt();
        System.out.print("Enter Difficulty (easy/hard): ");
        String diff = sc.next();

        Game game = new Game(n, x, diff);
        game.play();
    }
}