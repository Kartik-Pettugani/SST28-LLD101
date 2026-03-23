public class BallPen implements PenInterface{
    private String color;
    private boolean hasCap;
    private int inkLevel;

    public BallPen(String color, boolean hasCap) {
        this.color = color;
        this.hasCap = hasCap;
        this.inkLevel = 100;
    }

    public void start(){
        System.out.println("BallPen is started");
    }

    public void write(String text){
        if(inkLevel>0 && !hasCap){
            System.out.println("Writing: " + text);
            inkLevel-=1;
        } else if(hasCap){
            System.out.println("Please open the cap before writing.");
        } else {
            System.out.println("Ink level is too low to write.");
        }
    }
    
    public void close(){
        System.out.println("Ballpen is closed");
    }

    public String getColor(){
        return color;
    }

    public void refill(){
        inkLevel = 100;
        System.out.println("BallPen is refilled");
    }
}
