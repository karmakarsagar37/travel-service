package com.nymble.travel.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Represents a gold-tier passenger, extending the base {@link Passenger} class.
 * Gold passengers enjoy a 10% discount on all activity sign-ups. This class inherits
 * common properties from the Passenger class and provides a specific implementation
 * of the signUpForActivity method that applies the discount before checking the passenger's
 * balance and signing up for the desired activity.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
class GoldPassenger extends Passenger {
    private double balance; // The financial balance of the gold-tier passenger

    /**
     * Allows a gold passenger to sign up for an activity with a 10% discount on the cost.
     * This method overrides the abstract signUpForActivity method in the {@link Passenger} class,
     * providing a concrete implementation that applies the discount, checks the passenger's balance,
     * and then signs the passenger up for the activity if their balance is sufficient.
     *
     * @param activity The activity the gold-tier passenger wishes to sign up for.
     * @return true if the gold-tier passenger successfully signs up for the activity, taking into account the discount; false otherwise.
     */
    @Override
    public boolean signUpForActivity(Activity activity) {
        double discountedCost = activity.getCost() * 0.9; // Calculate the cost after applying a 10% discount
        if (balance >= discountedCost && activity.addPassenger(this)) {
            balance -= discountedCost; // Deduct the discounted cost from the gold-tier passenger's balance
            return true;
        }
        return false;
    }
}
