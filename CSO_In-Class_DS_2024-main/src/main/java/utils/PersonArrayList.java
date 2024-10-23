package utils;

import model.Person;

// Create a class called ArrayList (CapWords for class names)
public class PersonArrayList {
    // Create attributes for class
    // Every ArrayList will contain its own array of Person objects
    private Person[] data;
    // Every ArrayList will contain its own count of elements
    private int count;
    private final int expansionFactor;

    // Create a constructor (equivalent to __init__) to set up each new ArrayList

    /**
     * Default constructor for ArrayList.
     * <p>
     * This will initialise the internal array to a size of 10. Subsequent increases are made by the grow() method.
     */
    public PersonArrayList() {
        this(10);
    }

    public PersonArrayList(int expansionFactor) {
        if(expansionFactor < 0){
            throw new IllegalArgumentException("Expansion factor must be greater than 0");
        }
        this.expansionFactor = expansionFactor;
        data = new Person[10];
        count = 0;
    }

    /**
     * Add a new element to the list.
     * <p>
     * Where there is insufficient space remaining in the internal array, the array size will be increased using the
     * grow() method.
     *
     * @param person The value to be added.
     * @return True. (There is no situation where this will not add)
     */
    public boolean add(Person person) {
        if (data.length == count) {
            grow();
        }
        // Add the new element to the end of the list (first available space in the array)
        data[count] = person;
        // Increase the number of elements in the list
        count++;
        // Return true as the element has been added successfully
        return true;
    }

    /**
     * Get the element at the specified position.
     *
     * @param index The position from which to retrieve the element.
     * @return The element at the specified position.
     * @throws IndexOutOfBoundsException Where the supplied position is outside the bounds of the data in the array.
     */
    public Person get(int index) {
        // Check if the position is valid, i.e. not AFTER the end of the list or BEFORE the start
        if (index >= count || index < 0) {
            // If it's outside the bounds of the list, throw an exception
            throw new IndexOutOfBoundsException("Position supplied must be between 0 and size of list.");
        }
        // If the requested position is valid, return the data at that position
        return data[index];
    }

    /**
     * Get the number of elements currently stored in the list.
     *
     * @return The number of elements present in the list.
     */
    public int size() {
        // Return the number of elements in the list
        return count;
    }

    /**
     * Increase the size of the internal array. This will increase the size of the internal array by 15 slots each
     * time it is called.
     */
    private void grow() {
        Person[] enlargedArray = new Person[data.length + expansionFactor];

        for (int i = 0; i < data.length; i++) {
            enlargedArray[i] = data[i];
        }

        data = enlargedArray;
    }

    /**
     * Deletes a value from a specified position.
     *
     * @param pos The position to delete from.
     * @return The value deleted from the list.
     * @throws ArrayIndexOutOfBoundsException If the position to be deleted from is outside the boundaries of the array.
     */
    public Person shiftDelete(int pos) {
        validatePosition(pos);

        Person deleted = data[pos];

        for (int i = pos; i < count - 1; i++) {
            data[i] = data[i + 1];
        }

        data[data.length - 1] = null;
        count--;

        return deleted;
    }

    private void validatePosition(int pos) {
        if (pos < 0 || pos >= count) {
            throw new ArrayIndexOutOfBoundsException("Supplied position must be within the boundary of the array");
        }
    }
}
