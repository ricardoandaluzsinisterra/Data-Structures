package applications;

import model.Person;
import java.util.Scanner;

import utils.IOUtils;
import utils.PersonArrayList;

public class SamplePersonArrayList {
    private static Scanner input = new Scanner(System.in);

    public static Person getPerson(){
        System.out.println("Please enter the following information: ");
        System.out.println("First name: ");
        String fName = input.nextLine();

        System.out.println("Last name: ");
        String lName = input.nextLine();

        int age = IOUtils.getValidInteger("Age: ", 0, 150);

        return new Person(fName, lName, age);
    }

    public static Person findOldest(PersonArrayList people){
        if(people == null){
            throw new IllegalArgumentException("Person list cannot be null");
        }

        if(people.size() == 0){
            return null;
        }

        Person max = people.get(0);
        for (int i = 1; i < people.size(); i++) {
            Person current = people.get(i);
            if(current.getAge() > max.getAge()){
                max = current;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        PersonArrayList people = new PersonArrayList();
        for (int i = 0; i < 5; i++) {
            System.out.println("Adding Person #" + (i+1));
            Person p = getPerson();
            people.add(p);
        }

        IOUtils.display(people);

        // Display the details of the oldest person in the list
        Person oldest = findOldest(people);
        if(oldest != null) {
            System.out.println("The oldest person is " + oldest.getFirstName() + " " + oldest.getLastName() + ". They are " + oldest.getAge() + ".");
        }else{
            System.out.println("There are no people in the list.");
        }
    }

}
