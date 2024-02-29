package com.nymble.travel.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a standard passenger with a balance and the ability to sign up for activities.
 * This class extends the {@link Passenger} class, adding a balance field that represents
 * the passenger's current financial balance for signing up for activities. The signUpForActivity
 * method is overridden to check if the passenger has enough balance before signing up for an activity.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StandardPassenger extends Passenger {
    private double balance; // The financial balance of the passenger
    private String name; // The name of the passenger
    private int passengerNumber; // A unique number identifying the passenger

    /**
     * Attempts to sign up the passenger for a specified activity. The passenger can only sign up
     * if they have sufficient balance to cover the cost of the activity and if the activity
     * can accommodate more passengers. If the passenger successfully signs up, their balance
     * is reduced by the cost of the activity.
     *
     * @param activity The activity the passenger wishes to sign up for.
     * @return true if the passenger successfully signs up for the activity, false otherwise.
     */
    @Override
    public boolean signUpForActivity(Activity activity) {
        if (balance >= activity.getCost() && activity.addPassenger(this)) {
            balance -= activity.getCost(); // Deduct the cost of the activity from the passenger's balance
            return true;
        }
        return false;
    }
}
