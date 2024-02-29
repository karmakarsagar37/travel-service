package com.nymble.travel.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a premium passenger, extending the base {@link Passenger} class.
 * Premium passengers have the benefit of signing up for activities without any cost,
 * distinguishing them from other types of passengers. This class inherits common properties
 * from the Passenger class, such as balance, name, and passenger number, and provides a
 * specific implementation of the signUpForActivity method tailored to premium passengers.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
class PremiumPassenger extends Passenger {
    private double balance; // The financial balance of the passenger, not used in signUpForActivity for premium passengers
    private String name; // The name of the passenger
    private int passengerNumber; // A unique number identifying the passenger

    /**
     * Allows a premium passenger to sign up for an activity without deducting any cost from
     * their balance. This method overrides the abstract signUpForActivity method in the
     * {@link Passenger} class, providing a concrete implementation that reflects the benefits
     * of being a premium passenger.
     *
     * @param activity The activity the passenger wishes to sign up for.
     * @return true if the passenger successfully signs up for the activity, false otherwise.
     */
    @Override
    public boolean signUpForActivity(Activity activity) {
        return activity.addPassenger(this); // Premium passengers can sign up for activities at no cost
    }
}
