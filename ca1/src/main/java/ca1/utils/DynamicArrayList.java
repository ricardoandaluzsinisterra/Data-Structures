package ca1.utils;
import ca1.model.Job;

public class DynamicArrayList {

    private Job[] jobs;
    private int size;
    private int expansionFactor;
    
    private static int defaultCapacity = 10;
    private static int defaultExpansionFactor = 2;

    public DynamicArrayList() {
        this.jobs = new Job[defaultCapacity];
        this.size = 0;
        this.expansionFactor = defaultExpansionFactor;
    }

    public DynamicArrayList(int expansionFactor) {
        if (expansionFactor <= 1) {
            throw new IllegalArgumentException("Expansion factor must be greater than 1.");
        }
        this.jobs = new Job[defaultCapacity];
        this.size = 0;
        this.expansionFactor = expansionFactor;
    }

    public int size() {
        return size;
    }

    public Job get(int position) {
        validateIndex(position, false);
        return jobs[position];
    }

    public int indexOf(Job job) {
        for (int i = 0; i < size; i++) {
            if (jobs[i].equals(job)) {
                return i;
            }
        }
        return -1;
    }

    public void add(Job job) {
        validateJob(job);
        if (size == jobs.length) {
            expandArray();
        }
        jobs[size++] = job;
    }

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

    public Job set(Job job, int position) {
        validateJob(job);
        validateIndex(position, false);
        Job oldJob = jobs[position];
        jobs[position] = job;
        return oldJob;
    }

    public Job remove(int position) {
        validateIndex(position, false);
        Job removedJob = jobs[position];
        for (int i = position; i < size - 1; i++) {
            jobs[i] = jobs[i + 1];
        }
        jobs[--size] = null;
        return removedJob;
    }

    public boolean removeAll(Job job) {
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

    private void expandArray() {
        Job[] newJobs = new Job[jobs.length * expansionFactor];
        System.arraycopy(jobs, 0, newJobs, 0, jobs.length);
        jobs = newJobs;
    }

    private void validateIndex(int position, boolean allowEqual) {
        if (position < 0 || position > size || (!allowEqual && position == size)) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }
    }

    private void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }
    }
}