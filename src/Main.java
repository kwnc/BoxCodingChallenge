public class Main {

    public static void main(String[] args) {
        EventStreamProcessor processor = new EventStreamProcessor();

        processor.processEvent(new Event(4, 456, "Box", 7));
        processor.processEvent(new Event(5, 466, "AAA", 1));
        processor.processEvent(new Event(6, 506, "xyz", 3));

        processor.processEvent(new Event(10, 456, "abcd", 4));
        processor.processEvent(new Event(11, 466, "abcde", 5));
        processor.processEvent(new Event(12, 506, "abcdef", 7));
        processor.processEvent(new Event(13, 520, "a", 7));
        processor.processEvent(new Event(14, 570, "abcde", 5));

        processor.processEvent(new Event(21, 450, "abcd", 4));
        processor.processEvent(new Event(22, 460, "abcde", 5));
        processor.processEvent(new Event(23, 530, "abcdef", 7));
        processor.processEvent(new Event(24, 460, "a", 7));
        processor.processEvent(new Event(25, 570, "abcd", 4));

        processor.processEvent(new Event(31, 10, "abcd", 4));
        processor.processEvent(new Event(32, 50, "abcde", 5));
        processor.processEvent(new Event(33, 30, "ab", 5));
        processor.processEvent(new Event(34, 20, "abcdef", 7));
        processor.processEvent(new Event(35, 90, "ab", 5));
        processor.processEvent(new Event(36, 120, "a", 7));
    }
}