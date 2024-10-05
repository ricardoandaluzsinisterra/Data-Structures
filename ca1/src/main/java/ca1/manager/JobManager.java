package ca1.manager;

import ca1.model.Job;
import ca1.utils.DynamicArrayList;

import java.time.LocalDateTime;
import java.util.Scanner;

public class JobManager {

    private static Scanner scanner = new Scanner(System.in);
    private static DynamicArrayList jobList = new DynamicArrayList();

    public static void main(String[] args) {
        System.out.print("How many entries would you like to add to your job manager? ");
        int numEntries = scanner.nextInt();
        scanner.nextLine();

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
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

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

    private static Job createJobFromUserInput() {
        System.out.print("Enter Job ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
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

    private static void displayJobs() {
        System.out.println("\nJobs in the manager:");
        for (int i = 0; i < jobList.size(); i++) {
            Job job = jobList.get(i);
            System.out.println(job);
        }
    }

    private static void setJob() {
        System.out.print("Enter position to set: ");
        int position = scanner.nextInt();
        scanner.nextLine(); 
        Job job = createJobFromUserInput();
        jobList.set(job, position);
    }

    private static void removeJob() {
        System.out.print("Enter position to remove: ");
        int position = scanner.nextInt();
        scanner.nextLine(); 
        jobList.remove(position);
    }

    private static void searchJob() {
        System.out.print("Enter Job ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Job searchJob = new Job();
        searchJob.setId(id);
        int index = jobList.indexOf(searchJob);
        if (index != -1) {
            System.out.println("Job found at position: " + index);
        } else {
            System.out.println("Job not found.");
        }
    }
}