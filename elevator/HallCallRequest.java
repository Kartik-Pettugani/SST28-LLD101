package elevator;

import java.time.Instant;
import java.util.UUID;

public record HallCallRequest(UUID requestId, int floor, Direction direction, Instant createdAt) {
    public static HallCallRequest create(int floor, Direction direction) {
        return new HallCallRequest(UUID.randomUUID(), floor, direction, Instant.now());
    }
}
