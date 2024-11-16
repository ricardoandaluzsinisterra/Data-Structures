package utils;

import model.Event;

public class ArrayList {

    private Event[] events;
    private int size;
    private int expansionFactor;

    private static int defaultCapacity = 10;
    private static int defaultExpansionFactor = 2;

    public ArrayList() {
        this.events = new Event[defaultCapacity];
        this.size = 0;
        this.expansionFactor = defaultExpansionFactor;
    }

    public ArrayList(int expansionFactor) {
        if (expansionFactor <= 1) {
            throw new IllegalArgumentException("Expansion factor must be greater than 1.");
        }
        this.events = new Event[defaultCapacity];
        this.size = 0;
        this.expansionFactor = expansionFactor;
    }

    public int size() {
        return size;
    }

    public Event get(int position) {
        validateIndex(position, false);
        return events[position];
    }

    public int indexOf(Event Event) {
        for (int i = 0; i < size; i++) {
            if (events[i].equals(Event)) {
                return i;
            }
        }
        return -1;
    }

    public void add(Event Event) {
        validateEvent(Event);
        if (size == events.length) {
            expandArray();
        }
        events[size++] = Event;
    }

    public void add(Event Event, int position) {
        validateEvent(Event);
        validateIndex(position, true);
        if (size == events.length) {
            expandArray();
        }
        for (int i = size; i > position; i--) {
            events[i] = events[i - 1];
        }
        events[position] = Event;
        size++;
    }

    public Event set(Event Event, int position) {
        validateEvent(Event);
        validateIndex(position, false);
        Event oldEvent = events[position];
        events[position] = Event;
        return oldEvent;
    }

    public Event remove(int position) {
        validateIndex(position, false);
        Event removedEvent = events[position];
        for (int i = position; i < size - 1; i++) {
            events[i] = events[i + 1];
        }
        events[--size] = null;
        return removedEvent;
    }

    public boolean removeAll(Event Event) {
        validateEvent(Event);
        boolean removed = false;
        for (int i = 0; i < size; i++) {
            if (events[i].equals(Event)) {
                remove(i);
                i--; // Adjust index after removal
                removed = true;
            }
        }
        return removed;
    }

    private void expandArray() {
        Event[] newevents = new Event[events.length * expansionFactor];
        System.arraycopy(events, 0, newevents, 0, events.length);
        events = newevents;
    }

    private void validateIndex(int position, boolean allowEqual) {
        if (position < 0 || position > size || (!allowEqual && position == size)) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
    }

    private void validateEvent(Event Event) {
        if (Event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
    }
}