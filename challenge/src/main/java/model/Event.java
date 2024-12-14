package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event implements Comparable<Event> {
    public final static String[] VALID_SOURCES = {"HEAT", "TEMP", "PLUG", "MOTION", "CAMERA"};
    private UUID id;
    private LocalDateTime timestamp;
    private String source;
    private String description;

    /**
     * Constructs an Event with the specified ID, timestamp, source, and description.
     *
     * @param id          the unique identifier of the event
     * @param timestamp   the timestamp of the event
     * @param source      the source of the event
     * @param description the description of the event
     */
    public Event(UUID id, LocalDateTime timestamp, String source, String description) {
        validateEvent(id, timestamp, source, description);
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.description = description;
    }

    /**
     * Constructs an Event with the specified source and description.
     *
     * @param source      the source of the event
     * @param description the description of the event
     */
    public Event(String source, String description) {
        validateEvent(source, description);
        this.source = source;
        this.description = description;
    }

    /**
     * Returns the unique identifier of the event.
     *
     * @return the unique identifier of the event
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the timestamp of the event.
     *
     * @return the timestamp of the event
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the source of the event.
     *
     * @return the source of the event
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Returns a formatted string representation of the event.
     *
     * @return a formatted string representation of the event
     */
    public String format() {
        return "Event Details:\n" +
                "ID: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Source: " + source + "\n" +
                "Description: " + description;
    }

    /**
     * Compares this event to another event based on their timestamps.
     *
     * @param other the other event to compare to
     * @return a negative integer, zero, or a positive integer as this event is less than, equal to, or greater than the specified event
     */
    @Override
    public int compareTo(Event other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    /**
     * Validates the event with the specified source and description.
     *
     * @param source      the source of the event
     * @param description the description of the event
     */
    public static void validateEvent(String source, String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        validateSource(source);
    }

    /**
     * Validates the specified source.
     *
     * @param source the source to be validated
     */
    public static void validateSource(String source) {
        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }

    /**
     * Validates the specified array of events.
     *
     * @param eventArray the array of events to be validated
     */
    public static void validateArrayOfEvents(Event[] eventArray) {
        if (eventArray == null || eventArray.length == 0) {
            throw new IllegalArgumentException("Event array cannot be null nor empty.");
        }
        for (Event event : eventArray) {
            if (event == null) {
                throw new IllegalArgumentException("Event array cannot contain null elements.");
            }
        }
    }

    /**
     * Validates the specified event.
     *
     * @param event the event to be validated
     */
    public static void validateEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }

    /**
     * Validates the event with the specified ID, timestamp, source, and description.
     *
     * @param id          the unique identifier of the event
     * @param timestamp   the timestamp of the event
     * @param source      the source of the event
     * @param description the description of the event
     */
    public static void validateEvent(UUID id, LocalDateTime timestamp, String source, String description) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }
}