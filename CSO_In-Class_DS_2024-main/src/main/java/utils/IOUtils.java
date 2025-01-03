package utils;

import java.util.Scanner;

public class IOUtils {
    public static int getValidInteger(String prompt){
        Scanner input = new Scanner(System.in);
        boolean validInt = false;
        int num = 0;

        while(!validInt){
            System.out.println(prompt);
            if (input.hasNextInt()){
                // If they did, take it in and store it
                num = input.nextInt();
                // Change flag to show we received a number
                validInt = true;
            }else{
                // Inform the user that they need to enter a number
                System.out.println("Please enter a number next time!");
                // Clear the scanner of previous (invalid) input
                // Without this we can't take in new information - infinite loop!
                input.nextLine();
            }
        }

        // Return the num
        return num;
    }

    // Create a reusable method to take in a number within a specified range
    public static int getValidInteger(String prompt, int lower, int upper){
        // If the upper boundary value is less than the lower boundary, end the method
        // (this approach [throwing an exception] wouldn't be expected in your work yet!)
        if(lower > upper){
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }

        boolean valid = false;
        int num = 0;

        while(!valid){
            // Use standard getValidInteger to get an int from the command line
            // (Don't bother rewriting this logic!!)
            num = getValidInteger(prompt);
            // Check if that int is within the allowable range
            // - if yes then end the loop, otherwise keep going
            if(num >= lower && num <= upper) {
                valid = true;
            }else{
                System.out.println("Value must be between " + lower + " and " + upper);
            }
        }

        return num;
    }

    public static void display(PersonArrayList people) {
        // Display all information about each person in the list
        for (int i = 0; i < people.size(); i++) {
            System.out.println("Person #" + (i + 1) + " = " + people.get(i));
        }
    }
}

