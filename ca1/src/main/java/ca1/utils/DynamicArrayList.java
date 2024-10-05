package ca1.utils;

import ca1.model.Job;

/**
 * A dynamic array list implementation for storing Job objects.
 */
public class DynamicArrayList {

    private Job[] jobs;
    private int size;
    private int expansionFactor;

    private static int defaultCapacity = 10;
    private static int defaultExpansionFactor = 2;

    /**
     * Default constructor that initializes the array with default capacity and expansion factor.
     */
    public DynamicArrayList() {
        this.jobs = new Job[defaultCapacity];
        this.size = 0;
        this.expansionFactor = defaultExpansionFactor;
    }

    /**
     * Constructor that initializes the array with default capacity and a specified expansion factor.
     * @param expansionFactor the factor by which the array should expand when full.
     * @throws IllegalArgumentException if the expansion factor is less than or equal to 1.
     */
    public DynamicArrayList(int expansionFactor) {
        if (expansionFactor <= 1) {
            throw new IllegalArgumentException("Expansion factor must be greater than 1.");
        }
        this.jobs = new Job[defaultCapacity];
        this.size = 0;
        this.expansionFactor = expansionFactor;
    }

    /**
     * Returns the current size of the array.
     * @return the number of elements in the array.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the Job at the specified position.
     * @param position the index of the Job to return.
     * @return the Job at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Job get(int position) {
        validateIndex(position, false);
        return jobs[position];
    }

    /**
     * Returns the index of the first occurrence of the specified Job, or -1 if the array does not contain the Job.
     * @param job the Job to search for.
     * @return the index of the first occurrence of the specified Job, or -1 if the array does not contain the Job.
     */
    public int indexOf(Job job) {
        for (int i = 0; i < size; i++) {
            if (jobs[i].equals(job)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a Job to the end of the array.
     * @param job the Job to be added.
     * @throws IllegalArgumentException if the Job is null.
     */
    public void add(Job job) {
        validateJob(job);
        if (size == jobs.length) {
            expandArray();
        }
        jobs[size++] = job;
    }

    /**
     * Adds a Job at the specified position in the array.
     * @param job the Job to be added.
     * @param position the index at which the Job is to be inserted.
     * @throws IllegalArgumentException if the Job is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void add(Job job, int position) {
        validateJob(job);
        validateIndex(position, true);
        if (size == jobs.length) {
            expandArray();
        }
        for (int i = size; i > position; i--) {
            jobs[i] = jobs[i - 1];
        }
        jobs[position] = job;
        size++;
    }

    /**
     * Replaces the Job at the specified position with the specified Job.
     * @param job the Job to be stored at the specified position.
     * @param position the index of the Job to replace.
     * @return the Job previously at the specified position.
     * @throws IllegalArgumentException if the Job is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Job set(Job job, int position) {
        validateJob(job);
        validateIndex(position, false);
        Job oldJob = jobs[position];
        jobs[position] = job;
        return oldJob;
    }

    /**
     * Removes the Job at the specified position in the array.
     * @param position the index of the Job to be removed.
     * @return the Job that was removed from the array.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Job remove(int position) {
        validateIndex(position, false);
        Job removedJob = jobs[position];
        for (int i = position; i < size - 1; i++) {
            jobs[i] = jobs[i + 1];
        }
        jobs[--size] = null;
        return removedJob;
    }

    /**
     * Removes all occurrences of the specified Job from the array.
     * @param job the Job to be removed.
     * @return true if the array contained the specified Job, false otherwise.
     * @throws IllegalArgumentException if the Job is null.
     */
    public boolean removeAll(Job job) {
        validateJob(job);
        boolean removed = false;
        for (int i = 0; i < size; i++) {
            if (jobs[i].equals(job)) {
                remove(i);
                i--; // Adjust index after removal
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Expands the array by the expansion factor.
     */
    private void expandArray() {
        Job[] newJobs = new Job[jobs.length * expansionFactor];
        System.arraycopy(jobs, 0, newJobs, 0, jobs.length);
        jobs = newJobs;
    }

    /**
     * Validates the index for accessing the array.
     * @param position the index to be validated.
     * @param allowEqual whether the index can be equal to the size of the array.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    private void validateIndex(int position, boolean allowEqual) {
        if (position < 0 || position > size || (!allowEqual && position == size)) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
    }

    /**
     * Validates the Job object.
     * @param job the Job to be validated.
     * @throws IllegalArgumentException if the Job is null.
     */
    private void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }
    }
}