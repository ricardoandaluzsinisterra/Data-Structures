package ca1.manager;

import ca1.model.Job;
import ca1.utils.DynamicArrayList;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * JobManager class to manage a list of jobs using DynamicArrayList.
 */
public class JobManager {

    private static Scanner scanner = new Scanner(System.in);
    private static DynamicArrayList jobList = new DynamicArrayList();

    /**
     * Main method to run the JobManager application.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int numEntries = 0;
        while (true) {
            try {
                System.out.print("How many entries would you like to add to your job manager? ");
                numEntries = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        for (int i = 0; i < numEntries; i++) {
            Job job = createJobFromUserInput();
            jobList.add(job);
        }

        displayJobs();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Job");
            System.out.println("2. Set Job");
            System.out.println("3. Remove Job");
            System.out.println("4. Search Job");
            System.out.println("5. Display Jobs");
            System.out.println("6. Exit");
            System.out.print("Choose an action: ");
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
                    Job job = createJobFromUserInput();
                    jobList.add(job);
                    break;
                case 2:
                    setJob();
                    break;
                case 3:
                    removeJob();
                    break;
                case 4:
                    searchJob();
                    break;
                case 5:
                    displayJobs();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Creates a Job instance from user input.
     * @return a new Job instance.
     */
    private static Job createJobFromUserInput() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Job ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        System.out.print("Enter Job Owner: ");
        String owner = scanner.nextLine();
        System.out.print("Enter Job Description: ");
        String description = scanner.nextLine();
        LocalDateTime creationTime = LocalDateTime.now();
        Job job = new Job();
        job.setId(id);
        job.setOwner(owner);
        job.setDescription(description);
        job.setCreated(creationTime);
        return job;
    }

    /**
     * Displays all jobs in the job manager.
     */
    private static void displayJobs() {
        System.out.println("\nJobs in the manager:");
        for (int i = 0; i < jobList.size(); i++) {
            Job job = jobList.get(i);
            System.out.println((i + 1) + ". " + job);
        }
    }

    /**
     * Sets a job at a specified position in the job manager.
     */
    private static void setJob() {
        int position = 0;
        while (true) {
            try {
                System.out.print("Enter position to set: ");
                position = scanner.nextInt();
                scanner.nextLine();
                if (position < 1 || position > jobList.size()) {
                    System.out.println("Invalid position. Please enter a number between 1 and " + jobList.size());
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        Job job = createJobFromUserInput();
        jobList.set(job, position - 1);
    }

    /**
     * Removes a job at a specified position in the job manager.
     */
    private static void removeJob() {
        int position = 0;
        while (true) {
            try {
                System.out.print("Enter position to remove: ");
                position = scanner.nextInt();
                scanner.nextLine();
                if (position < 1 || position > jobList.size()) {
                    System.out.println("Invalid position. Please enter a number between 1 and " + jobList.size());
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        jobList.remove(position - 1);
    }

    /**
     * Searches for a job by ID in the job manager.
     */
    private static void searchJob() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Job ID to search: ");
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        Job searchJob = new Job();
        searchJob.setId(id);
        int index = jobList.indexOf(searchJob);
        if (index != -1) {
            System.out.println("Job found at position: " + (index + 1));
        } else {
            System.out.println("Job not found.");
        }
    }
}