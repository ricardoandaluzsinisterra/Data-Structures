package utils;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import exceptions.DuplicateElementException;
import model.Event;

/**
 * A bounded priority queue set that maintains a list of events with a maximum size.
 * The events are ordered based on their natural ordering.
 */
public class BoundedPriorityQueueSet {
    private final LinkedList<Event> list;
    private final int maximumSize;
    private Event first;
    private Event last;

    /**
     * Constructs a BoundedPriorityQueueSet with a default maximum size of 10.
     */
    public BoundedPriorityQueueSet() {
        this.list = new LinkedList<>();
        this.maximumSize = 10;
        this.first = null;
        this.last = null;
    }

    /**
     * Constructs a BoundedPriorityQueueSet with a specified maximum size.
     *
     * @param size the maximum size of the queue
     */
    public BoundedPriorityQueueSet(int size) {
        this.list = new LinkedList<>();
        this.maximumSize = size;
        this.first = null;
        this.last = null;
    }

    /**
     * Returns the number of events in the queue.
     *
     * @return the size of the queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return list.size() == this.maximumSize;
    }

    /**
     * Adds an event to the queue. The event is inserted based on its natural ordering.
     *
     * @param event the event to be added
     * @throws IllegalStateException if the queue is full
     * @throws DuplicateElementException if the event is already in the queue
     */
    public void add(Event event) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (list.contains(event)) {
            throw new DuplicateElementException("The event is already in the queue");
        }

        if (isEmpty()) {
            list.add(event);
        } else if (event.compareTo(list.getFirst()) > 0) {
            list.addFirst(event);
        } else if (event.compareTo(list.getLast()) < 0) {
            list.addLast(event);
        } else {
            int index = 0;
            for (Event e : list) {
                if (event.compareTo(e) > 0) {
                    list.add(index, event);
                    break;
                }
                index++;
            }
        }

        updateFirstAndLast();
    }

    /**
     * Offers an event to the queue. The event is inserted based on its natural ordering.
     *
     * @param event the event to be offered
     * @return true if the event was added, false otherwise
     */
    public boolean offer(Event event){
        if (isFull() || list.contains(event)) {
            return false;
        }

        add(event);
        updateFirstAndLast();
        return true;
    }

    /**
     * Updates the first and last events in the queue.
     */
    private void updateFirstAndLast() {
        if (!list.isEmpty()) {
            first = list.getFirst();
            last = list.getLast();
        } else {
            first = null;
            last = null;
        }
    }

    /**
     * Checks if the queue is empty and throws an exception if it is.
     *
     * @throws NoSuchElementException if the queue is empty
     */
    private void checkIfEmptyAndThrow() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    /**
     * Retrieves, but does not remove, the first event in the queue.
     *
     * @return the first event in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Event element() {
        checkIfEmptyAndThrow();
        return list.getFirst();
    }

    /**
     * Retrieves, but does not remove, the first event in the queue, or returns null if the queue is empty.
     *
     * @return the first event in the queue, or null if the queue is empty
     */
    public Event peek() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    /**
     * Retrieves and removes the first event in the queue.
     *
     * @return the first event in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Event remove() {
        checkIfEmptyAndThrow();
        Event firstEvent = list.getFirst();
        list.removeFirst();
        return firstEvent;
    }

    /**
     * Retrieves and removes the first event in the queue, or returns null if the queue is empty.
     *
     * @return the first event in the queue, or null if the queue is empty
     */
    public Event poll() {
        if (isEmpty()) {
            return null;
        }
        Event firstEvent = list.getFirst();
        list.removeFirst();
        return firstEvent;
    }

    /**
     * Retrieves, but does not remove, the first event in the queue.
     *
     * @return the first event in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Event getFirst() {
        checkIfEmptyAndThrow();
        return first;
    }

    /**
     * Retrieves, but does not remove, the last event in the queue.
     *
     * @return the last event in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Event getLast() {
        checkIfEmptyAndThrow();
        return last;
    }

}