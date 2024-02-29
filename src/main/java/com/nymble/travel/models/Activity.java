package com.nymble.travel.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * Represents an activity that passengers can enroll in during their travel.
 * This class includes details about the activity such as its name, description,
 * cost, and capacity. It also tracks the number of enrolled passengers.
 */
@Data
@Builder
public class Activity {
    private String name; // Name of the activity
    private String description; // Description of the activity
    private double cost; // Cost to enroll in the activity
    private int capacity; // Maximum number of passengers that can enroll
    // Disable the setter for enrolledPassengers since this is incremented only when addPassenger method is invoked
    @Setter(AccessLevel.NONE)
    private int enrolledPassengers = 0; // Current number of enrolled passengers

    /**
     * Attempts to add a passenger to this activity. If the activity has capacity,
     * the passenger is added, and the method returns true. If the activity is at capacity,
     * the method returns false, and no passenger is added.
     *
     * @param passenger The passenger to be added to the activity.
     * @return true if the passenger was successfully added, false otherwise.
     */
    public boolean addPassenger(Passenger passenger) {
        if (enrolledPassengers < capacity) {
            enrolledPassengers++;
            return true;
        }
        return false;
    }

    /**
     * Prints a list of available activities to the console. An activity is considered
     * available if its capacity is greater than the number of enrolled passengers.
     * The method prints the name, available spaces, cost, and description of each available activity.
     *
     * @param activities The list of activities to be checked and printed.
     */
    public static void printAvailableActivities(List<Activity> activities) {
        for (Activity activity : activities) {
            if (activity.getCapacity() > activity.enrolledPassengers) { // Check for available capacity
                System.out.println("Activity: " + activity.getName());
                System.out.println("    Spaces Available: " + (activity.getCapacity() - activity.enrolledPassengers));
                System.out.println("    Cost: " + activity.getCost());
                System.out.println("    Description: " + activity.getDescription());
            }
        }
    }
}
