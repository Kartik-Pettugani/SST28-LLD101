import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate post-creation "updates" that create NEW instances
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter service updates (new instances):");
        System.out.println("Original : " + t);
        System.out.println("Assigned : " + assigned);
        System.out.println("Escalated: " + escalated);

        // Demonstrate external mutation via leaked list reference
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nTried mutating tags list returned by getTags(); ticket remains unchanged:");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags list is immutable from outside (as intended).");
        }
        System.out.println("Ticket after attempted external tag mutation: " + escalated);
    }
}
