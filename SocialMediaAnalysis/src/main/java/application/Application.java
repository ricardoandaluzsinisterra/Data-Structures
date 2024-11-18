package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.PostEngagement;
import utils.ArrayList;
import utils.FileHandlingUtilities;


    public class Application {
        private static final Scanner scanner = new Scanner(System.in);

        public static void displayAndSort(ArrayList engagements){
            engagements.bubbleSort();
            engagements.display();
        }

        public static void filterAndDisplay(ArrayList engagements, String postID){
            ArrayList filteredArray = engagements.filter(postID);
            if (filteredArray.size() == 0){
                System.out.println("Sorry, post was not found!");
            }
            filteredArray.display();
        }

        public static void postsInBothDatasets(ArrayList array1, ArrayList array2){
            
        }




        public static void main(String[] args) {
            PostEngagement[] engagementsArray = FileHandlingUtilities.readPostEngagementFile("C:\\Users\\code\\SocialMediaAnalysis\\datasets.\\engagements_1.txt");
            PostEngagement[] engagementsArray2 = FileHandlingUtilities.readPostEngagementFile("C:\\Users\\code\\SocialMediaAnalysis\\datasets.\\engagements_2.txt");
            ArrayList engagements = new ArrayList();
            ArrayList engagements2 = new ArrayList();

            for (PostEngagement engagement : engagementsArray){
                engagements.add(engagement);
            }

            displayAndSort(engagements);
            while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Filter by post ID");
            System.out.println("2. Post with most engagements");
            System.out.println("6. Exit");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    String postID = scanner.nextLine().toUpperCase();
                    filterAndDisplay(engagements, postID);
                    break;
                case 2:
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        }

        
}
