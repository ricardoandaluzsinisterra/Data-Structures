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

}
