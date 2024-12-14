package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import exceptions.DuplicateElementException;
import model.Event;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

public class BoundedPriorityQueueSetTests {

    private BoundedPriorityQueueSet myQueue;

    @BeforeEach
    public void setUp() {
        myQueue = new BoundedPriorityQueueSet(10);
    }

    @Test
    void size() {
        assertEquals(0, myQueue.size());
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(1, myQueue.size());
    }

    @Test
    void isEmpty() {
        assertTrue(myQueue.isEmpty());
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertFalse(myQueue.isEmpty());
    }

    @Test
    void isFull() {
        BoundedPriorityQueueSet smallQueue = new BoundedPriorityQueueSet(1);
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        smallQueue.add(event);
        assertTrue(smallQueue.isFull());
        assertFalse(myQueue.isFull());
    }

    @Test
    void addToEmptyQueue() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(1, myQueue.size());
        assertEquals(event, myQueue.getFirst());
        assertEquals(event, myQueue.getLast());
    }

    @Test
    void addToFullQueue() {
        BoundedPriorityQueueSet smallQueue = new BoundedPriorityQueueSet(1);
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "TEMP", "description2");
        smallQueue.add(event1);
        assertThrows(IllegalStateException.class, () -> smallQueue.add(event2));
    }

    @Test
    void addDuplicateEvent() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertThrows(DuplicateElementException.class, () -> myQueue.add(event));
    }

    @Test
    void addHigherPriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now(), "TEMP", "description2");
        myQueue.add(event1);
        myQueue.add(event2);
        assertEquals(2, myQueue.size());
        assertEquals(event1, myQueue.getFirst());
        assertEquals(event2, myQueue.getLast());
    }

    @Test
    void addLowerPriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "TEMP", "description2");
        myQueue.add(event1);
        myQueue.add(event2);
        assertEquals(2, myQueue.size());
        assertEquals(event2, myQueue.getFirst());
        assertEquals(event1, myQueue.getLast());
    }

    @Test
    void addMiddlePriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(3), "TEMP", "description2");
        Event event3 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(2), "PLUG", "description3");
        myQueue.add(event1);
        myQueue.add(event2);
        myQueue.add(event3);
        assertEquals(3, myQueue.size());
        assertEquals(event2, myQueue.getFirst());
        assertEquals(event1, myQueue.getLast());
        assertEquals(event2, myQueue.element());
    }

    @Test
    void offer() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        assertTrue(myQueue.offer(event));
        assertEquals(1, myQueue.size());
        assertEquals(event, myQueue.getFirst());
        assertEquals(event, myQueue.getLast());
    }

    @Test
    void offerToFullQueue() {
        BoundedPriorityQueueSet smallQueue = new BoundedPriorityQueueSet(1);
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "TEMP", "description2");
        smallQueue.add(event1);
        assertFalse(smallQueue.offer(event2));
    }

    @Test
    void offerDuplicateEvent() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertFalse(myQueue.offer(event));
    }

    @Test
    void offerHigherPriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now(), "TEMP", "description2");
        myQueue.offer(event1);
        myQueue.offer(event2);
        assertEquals(2, myQueue.size());
        assertEquals(event1, myQueue.getFirst());
        assertEquals(event2, myQueue.getLast());
    }

    @Test
    void offerLowerPriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "TEMP", "description2");
        myQueue.offer(event1);
        myQueue.offer(event2);
        assertEquals(2, myQueue.size());
        assertEquals(event2, myQueue.getFirst());
        assertEquals(event1, myQueue.getLast());
    }

    @Test
    void offerMiddlePriorityEvent() {
        Event event1 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(1), "HEAT", "description1");
        Event event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(3), "TEMP", "description2");
        Event event3 = new Event(UUID.randomUUID(), LocalDateTime.now().plusSeconds(2), "PLUG", "description3");
        myQueue.offer(event1);
        myQueue.offer(event2);
        myQueue.offer(event3);
        assertEquals(3, myQueue.size());
        assertEquals(event2, myQueue.getFirst());
        assertEquals(event1, myQueue.getLast());
        assertEquals(event2, myQueue.element());
    }

    @Test
    void element() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.element());
    }

    @Test
    void elementThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> myQueue.element());
    }

    @Test
    void peek() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.peek());
    }

    @Test
    void peekReturnsNullWhenEmpty() {
        assertNull(myQueue.peek());
    }

    @Test
    void remove() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.remove());
        assertTrue(myQueue.isEmpty());
    }

    @Test
    void removeThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> myQueue.remove());
    }

    @Test
    void poll() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.poll());
        assertTrue(myQueue.isEmpty());
    }

    @Test
    void pollReturnsNullWhenEmpty() {
        assertNull(myQueue.poll());
    }

    @Test
    void getFirst() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.getFirst());
    }

    @Test
    void getFirstThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> myQueue.getFirst());
    }

    @Test
    void getLast() {
        Event event = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "description1");
        myQueue.add(event);
        assertEquals(event, myQueue.getLast());
    }

    @Test
    void getLastThrowsExceptionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> myQueue.getLast());
    }


}