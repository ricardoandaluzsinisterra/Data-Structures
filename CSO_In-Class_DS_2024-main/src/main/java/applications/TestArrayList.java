package applications;

import utils.ArrayList;

import java.util.Random;

public class TestArrayList {
    public static void main(String[] args) {
        // Create an instance of our list
        ArrayList nums = new ArrayList();

        // Create a new random generator
        Random randomGen = new Random();
        // Generate 10 random elements and add to our list
        for (int i = 0; i < 12; i++) {
            // Add the new element to the list
            nums.add(randomGen.nextInt());
        }

        // Loop through the list from start to end
        for (int i = 0; i < nums.size(); i++) {
            // Get current element from list and display
            System.out.println(nums.get(i));
        }
    }
}
