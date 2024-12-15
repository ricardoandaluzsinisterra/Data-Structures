package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void add_EmptyList() {
        ArrayList myList = new ArrayList();
        int value = 5;
        myList.add(value);

        assertEquals(1, myList.size());

        int retrieved = myList.get(0);
        assertEquals(value, retrieved);
    }

    @Test
    void add_PopulatedList() {
        ArrayList myList = new ArrayList();
        int [] values = {1, 5, 7, 9};
        for (int value : values) {
            myList.add(value);
        }

        assertEquals(values.length, myList.size());

        for (int i = 0; i < myList.size(); i++) {
            int retrieved = myList.get(i);
            assertEquals(values[i], retrieved);
        }
    }

    @Test
    void add_PopulatedList_GrowRequired() {
        ArrayList myList = new ArrayList();

        int [] values = new int[11];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        for (int value : values) {
            myList.add(value);
        }

        assertEquals(values.length, myList.size());

        for (int i = 0; i < myList.size(); i++) {
            int retrieved = myList.get(i);
            assertEquals(values[i], retrieved);
        }
    }

    @Test
    void testGet_ValidPosition(){
        ArrayList myList = new ArrayList();

        int expResult = 10;
        int [] values = new int[expResult+1];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        for (int value : values) {
            myList.add(value);
        }

        int result = myList.get(myList.size()-1);
        assertEquals(expResult, result);
    }

    @Test
    void testGet_InvalidPosition(){
        ArrayList myList = new ArrayList();

        int size = 10;
        int [] values = new int[size];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }

        for (int value : values) {
            myList.add(value);
        }

        // To check for an exception, assert that it is thrown when the appropriate situation arises.
        // Need to specify:
        // The name of the exception class
        // A lambda function in which the method under test is called
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myList.get(myList.size());
        });
    }
}