import java.time.Duration;
import java.time.LocalDateTime;

public class BillGenerator {
    public static double generateBill(ParkingTicket ticket, LocalDateTime outTime) {
        long durationMinutes = Duration.between(ticket.getInTime(), outTime).toMinutes();
        if (durationMinutes <= 0) durationMinutes = 1;

        double hourlyRate = switch (ticket.getAssignedSlot().getType()) {
            case SMALL -> 20.0;
            case MEDIUM -> 50.0;
            case LARGE -> 100.0;
        };

        return (durationMinutes / 60.0) * hourlyRate;
    }
}