public class Main {
    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(3, new NearestIdleStrategy());

        controller.setMaintenanceFloor(5, true); 
        
        controller.handleExternalRequest(new ExternalRequest(5, Direction.UP)); 
        
        controller.handleExternalRequest(new ExternalRequest(3, Direction.UP));
        
        controller.handleInternalRequest(0, new InternalRequest(10));
    }
}