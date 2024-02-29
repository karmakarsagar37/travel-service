package com.nymble.travel.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Serves as the base class for various types of passengers, including GoldPassenger,
 * PremiumPassenger, and StandardPassenger. This abstract class defines common properties
 * such as balance, name, and passenger number, which are shared among all types of passengers.
 * It also declares an abstract method, signUpForActivity, which must be implemented by
 * subclasses to define the logic for signing up for an activity based on their specific rules
 * and privileges.
 */
@Data
@SuperBuilder
public abstract class Passenger {
    protected double balance; // The financial balance of the passenger
    protected String name; // The name of the passenger
    protected int passengerNumber; // A unique number identifying the passenger

    /**
     * Abstract method to be implemented by subclasses to allow a passenger to sign up for an activity.
     * The implementation of this method in subclasses should take into account the passenger's balance,
     * the cost of the activity, and any specific rules or benefits applicable to the type of passenger.
     *
     * @param activity The activity the passenger wishes to sign up for.
     * @return true if the passenger successfully signs up for the activity, false otherwise.
     */
    public abstract boolean signUpForActivity(Activity activity);
}
