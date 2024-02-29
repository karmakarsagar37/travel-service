package com.nymble.travel.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a travel destination, encapsulating details such as its name
 * and a list of activities available at this destination. The class provides
 * functionality to add new activities to the destination.
 */
@Data
@Builder
public class Destination {
    private String name; // The name of the destination
    @Setter(AccessLevel.NONE)
    private List<Activity> activities = new ArrayList<>(); // A list of activities available at the destination

    /**
     * Adds a new activity to the list of activities available at this destination.
     * This method ensures that the destination's activity list is always up to date
     * with the offerings available to travelers.
     *
     * @param activity The activity to be added to this destination.
     */
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}
