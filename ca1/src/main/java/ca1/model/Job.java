package ca1.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a job with an ID, owner, description, and creation time.
 */
public class Job {
    private int id;
    private String owner;
    private String description;
    private LocalDateTime created;

    /**
     * Gets the job ID.
     * @return the job ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the job ID.
     * @param id the job ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the job owner.
     * @return the job owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the job owner.
     * @param owner the job owner.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the job description.
     * @return the job description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the job description.
     * @param description the job description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the job creation time.
     * @return the job creation time.
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Sets the job creation time.
     * @param created the job creation time.
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * Returns a string representation of the job.
     * @return a string representation of the job.
     */
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}