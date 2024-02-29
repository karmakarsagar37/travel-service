package com.nymble.travel.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the StandardPassenger class within the travel package.
 * It verifies the behavior of standard passengers when signing up for activities,
 * particularly focusing on the standard cost deduction and handling of insufficient
 * balance scenarios.
 */
class StandardPassengerTest {
    // Shared StandardPassenger instance for use in all test methods
    public static StandardPassenger standardPassenger;

    /**
     * Prepares the test environment before any tests are executed.
     * Initializes a StandardPassenger instance with a set balance, name, and passenger number
     * to be used in subsequent tests. This method is invoked once before all test methods.
     */
    @BeforeAll
    public static void setUp() {
        standardPassenger = StandardPassenger.builder()
                .passengerNumber(123456789)
                .name("Test")
                .balance(250)
                .build();
    }

    /**
     * Tests the signUpForActivity method for a scenario where the standard passenger
     * signs up for an activity without any discount. Verifies that the full activity
     * cost is deducted from the passenger's balance and that the sign-up process
     * is successful.
     */
    @Test
    void signUpForActivity_Discount_Successful() {
        // Set up an activity with a specified cost
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(200.0)
                .description("Test Description")
                .build();

        // Attempt to sign up for the activity and verify the outcome
        // For "StandardPassenger" it shall have no discount
        boolean result = standardPassenger.signUpForActivity(activity);

        // Assert the sign-up was successful and the balance was correctly deducted
        Assertions.assertTrue(result, "Sign up should be successful without a discount.");
        Assertions.assertEquals(standardPassenger.getBalance(), 50, "The remaining balance should be 50 after deducting the full activity cost.");
    }

    /**
     * Tests the signUpForActivity method for a scenario where the activity cost
     * exceeds the standard passenger's balance. Verifies that the sign-up process
     * is unsuccessful and the passenger's balance remains unchanged.
     */
    @Test
    void signUpForActivity_Activity_Cost_More_than_balance_unsuccessful() {
        // Set up an activity with a cost exceeding the passenger's balance
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(300.0)
                .description("Test Description")
                .build();

        // Attempt to sign up for the activity with insufficient balance
        boolean result = standardPassenger.signUpForActivity(activity);

        // Assert the sign-up was unsuccessful and the balance remains unchanged
        Assertions.assertFalse(result, "Sign up should fail due to insufficient balance.");
        Assertions.assertEquals(standardPassenger.getBalance(), 250, "The balance should remain unchanged after a failed sign-up attempt.");
    }
}
