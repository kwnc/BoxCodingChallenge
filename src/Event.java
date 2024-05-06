public class Event {
    int identifier;
    int timestamp;
    String content;
    int checksum;

    public Event(int identifier, int timestamp, String content, int checksum) {
        this.identifier = identifier;
        this.timestamp = timestamp;
        this.content = content;
        this.checksum = checksum;
    }
}