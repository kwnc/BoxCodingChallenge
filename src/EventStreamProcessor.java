import java.util.LinkedList;
import java.util.Queue;

public class EventStreamProcessor {
    Queue<Event> events = new LinkedList<>();
    Queue<Event> historicalEvents = new LinkedList<>();
    final int WINDOW_SIZE = 60;
    double historicalAverage;
    int historicalCount = 0;
    long startWindow = 0;
    long endWindow = 0;

    public void processEvent(Event event) {
        events.offer(event);

        if (!isValidChecksum(event)) {
            System.out.println("Event " + event.identifier + ": Invalid checksum");
            return;
        }

        int eventTimestamp = event.timestamp;
        if (eventTimestamp > startWindow && endWindow < eventTimestamp) {
            startWindow = eventTimestamp - WINDOW_SIZE;
            endWindow = eventTimestamp;
        }

        while (!events.isEmpty()) {
            Event oldEvent = events.poll();
            historicalEvents.offer(oldEvent);
            updateHistoricalAverage(oldEvent.content.length());
        }

        if (endWindow < eventTimestamp && (eventTimestamp - startWindow) > WINDOW_SIZE) {
            endWindow = eventTimestamp;
            startWindow = eventTimestamp - WINDOW_SIZE;
            historicalCount = 0;
            historicalAverage = 0;
        }

        if (eventTimestamp < startWindow) {
            System.out.println("Event " + event.identifier + ": Ignored, too late received, window starts " + (startWindow));
            return;
        }

        System.out.printf("Event %d: average %.2f, window ends %d%n", event.identifier, historicalAverage, endWindow);
    }

    private void updateHistoricalAverage(int length) {
        historicalAverage = (historicalAverage * historicalCount + length) / (++historicalCount);
    }

    private boolean isValidChecksum(Event event) {
        int asciiSum = event.content.chars().sum();
        int remainder = asciiSum % 10;
        return remainder == event.checksum;
    }
}
