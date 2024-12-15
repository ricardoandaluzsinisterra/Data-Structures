package applications;

import model.Person;
import model.PersonLastNameComparator;
import utils.IOUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WorkingWithComparator {
    static Scanner input = new Scanner(System.in);
    public static Person getPerson(){
        System.out.println("Please enter the following information: ");
        System.out.println("First name: ");
        String fName = input.nextLine();

        System.out.println("Last name: ");
        String lName = input.nextLine();

        int age = IOUtils.getValidInteger("Age: ", 0, 150);

        return new Person(fName, lName, age);
    }
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();

        for (int i = 0; i < 5; i++) {
            Person p = getPerson();
            people.add(p);
        }

        System.out.println("People: \n" + people);

        Collections.sort(people);
        System.out.println("People sorted by first name: \n" + people);
        Collections.sort(people, new PersonLastNameComparator());
        System.out.println("People sorted by last name: \n" + people);
    }
}
