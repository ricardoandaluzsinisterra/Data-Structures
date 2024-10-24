package utils;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import exceptions.DuplicateElementException;
import model.Event;

public class BoundedPriorityQueueSet {
    private final LinkedList<Event> list;
    private final int maximumSize;
    private Event first;
    private Event last;

    public BoundedPriorityQueueSet() {
        this.list = new LinkedList<>();
        this.maximumSize = 10;
        this.first = null;
        this.last = null;
    }

    public BoundedPriorityQueueSet(int size) {
        this.list = new LinkedList<>();
        this.maximumSize = size;
        this.first = null;
        this.last = null;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean isFull() {
        return list.size() == this.maximumSize;
    }

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

    public boolean offer(Event event){
        if (isFull() || list.contains(event)) {
            return false;
        }

        add(event);
        updateFirstAndLast();
        return true;
    }

    private void updateFirstAndLast() {
        if (!list.isEmpty()) {
            first = list.getFirst();
            last = list.getLast();
        } else {
            first = null;
            last = null;
        }
    }

    private void checkIfEmptyAndThrow() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    public Event element() {
        checkIfEmptyAndThrow();
        return list.getFirst();
    }

    public Event peek() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    public Event remove() {
        checkIfEmptyAndThrow();
        Event firstEvent = list.getFirst();
        list.removeFirst();
        return firstEvent;
    }

    public Event poll() {
        if (isEmpty()) {
            return null;
        }
        Event firstEvent = list.getFirst();
        list.removeFirst();
        return firstEvent;
    }

    public Event getFirst() {
        checkIfEmptyAndThrow();
        return first;
    }

    public Event getLast() {
        checkIfEmptyAndThrow();
        return last;
    }

}