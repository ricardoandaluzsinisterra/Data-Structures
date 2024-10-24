package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Event;
import model.EventGenerator;

/**
 * The UsingEvents class provides methods to manage and display events.
 */
public class UsingEvents {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The main method to run the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Create an array of 10 random events and display it:");
        Event[] events = new Event[10];
        for (int i = 0; i < events.length; i++) {
            events[i] = EventGenerator.generateEvent();
        }
        displayEvents(events);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display all Events");
            System.out.println("2. Display all Events from a specific source");
            System.out.println("3. Generate a new Event and insert it into the array");
            System.out.println("4. View active sources");
            System.out.println("5. Clear all events before a specific timestamp. This should display all cleared events");
            System.out.println("6. Exit the program");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    displayEvents(events);
                    break;
                case 2:
                    displayEventsFromSource(events);
                    break;
                case 3:
                    events = generateEventAndInsert(events);
                    displayEvents(events);
                    break;
                case 4:
                    displayActiveSources(events);
                    break;
                case 5:
                    clearEvents(events);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays all events in the given array.
     *
     * @param eventArray the array of events to display
     */
    public static void displayEvents(Event[] eventArray) {
        int count = 0;
        for (Event event : eventArray) {
            count += 1;
            System.out.println(event.format() + " In position: " + (count - 1));
        }
    }

    /**
     * Displays all events from a specific source.
     *
     * @param eventArray the array of events to search
     */
    public static void displayEventsFromSource(Event[] eventArray) {
        while (true) {
            try {
                System.out.println("Please enter a source");
                String source = scanner.next();
                scanner.nextLine();
                Event.validateSource(source);
                int counter = 0;
                for (Event event : eventArray) {
                    if (event.getSource().equals(source)) {
                        counter++;
                    }
                }

                if (counter == 0) {
                    System.out.println("No events found for the source: " + source);
                    break;
                }

                Event[] events = new Event[counter];
                int index = 0;
                for (Event event : eventArray) {
                    if (event.getSource().equals(source)) {
                        events[index++] = event;
                    }
                }
                System.out.println("Events from source: " + source);
                displayEvents(events);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid source, please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Generates a new event and inserts it into the array.
     *
     * @param eventArray the array of events to insert into
     */
    public static Event[] generateEventAndInsert(Event[] eventArray) {
        Event newEvent = EventGenerator.generateEvent();
        System.out.println(newEvent.format());
        Event[] events = Event.insertSorted(eventArray, newEvent);
        return events;
    }

    /**
     * Displays the active sources from the given array of events.
     *
     * @param eventArray the array of events to search
     */
    public static void displayActiveSources(Event[] eventArray) {
        String[] activeSources = Event.getActiveSources(eventArray);
        System.out.println("The active sources are the following:");
        for (String source : activeSources) {
            System.out.println("\n" + source);
        }
    }

    /**
     * Clears all events before a specific timestamp and displays the cleared events.
     *
     * @param eventArray the array of events to clear
     */
    public static void clearEvents(Event[] eventArray) {
        // Use the existing static scanner field
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime deleteBeforeTimestamp = null;

        while (deleteBeforeTimestamp == null) {
            System.out.print("Enter the timestamp (yyyy-MM-dd HH:mm:ss): ");
            String input = scanner.nextLine();
            try {
                deleteBeforeTimestamp = LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the timestamp in the format yyyy-MM-dd HH:mm:ss.");
            }
        }

        Event[] deletedEvents = Event.deleteAllBefore(eventArray, deleteBeforeTimestamp);
        System.out.println("Deleted events:");
        for (Event event : deletedEvents) {
            System.out.println(event.format());
        }
    }
}