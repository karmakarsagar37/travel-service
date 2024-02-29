package com.nymble.travel.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests for the GoldPassenger class within the travel package. These tests verify
 * the functionality related to signing up for activities, particularly focusing
 * on the application of discounts for gold passengers and the handling of cases
 * where activity costs exceed the passenger's balance.
 */
class GoldPassengerTest {
    // Static variable to hold the GoldPassenger instance used in all test methods.
    public static GoldPassenger goldPassenger;

    /**
     * Initializes a common GoldPassenger instance for all tests with predefined attributes.
     * This setup runs once before any of the test methods.
     */
    @BeforeEach
    public void setUp() {
        goldPassenger = GoldPassenger.builder()
                .passengerNumber(123456789)
                .name("Test")
                .balance(250)
                .build();
    }

    /**
     * Tests the signUpForActivity method for a scenario where the gold passenger
     * receives a discount. This test verifies that the discount is correctly applied
     * and that the passenger's balance is updated accordingly.
     */
    @Test
    void signUpForActivity_Discount_Successful() {
        // Create a test activity with specific cost
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(200.0)
                .description("Test Description")
                .build();

        // Action: Sign up for the activity with the expectation of a discount being applied
        // Should apply a discount of 10 % so remaining balance shall be 250 - (90 % of 200) = 70
        boolean result = goldPassenger.signUpForActivity(activity);

        // Assertions: Check if the sign-up was successful and if the balance is correctly updated after the discount
        Assertions.assertTrue(result, "Sign up should be successful with a discount applied.");
        Assertions.assertEquals(goldPassenger.getBalance(), 70, "The remaining balance should be 70 after applying a 10% discount to the activity cost.");
    }

    /**
     * Tests the signUpForActivity method for a scenario where the gold passenger
     * receives a discount. This test verifies that the discount is correctly applied
     * and that the passenger's balance is updated accordingly.
     */
    @Test
    void signUpForActivity_Successful_With_discount() {
        // Create a test activity with specific cost
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(270.0)
                .description("Test Description")
                .build();

        // Action: Sign up for the activity with the expectation of 10% discount being applied
        // Should apply a discount of 10 % so remaining balance shall be 250 - (90 % of 270) = 7
        boolean result = goldPassenger.signUpForActivity(activity);

        // Assertions: Check if the sign-up was successful and if the balance is correctly updated after the discount
        Assertions.assertTrue(result, "Sign up should be successful with a discount applied.");
        Assertions.assertEquals(goldPassenger.getBalance(), 7, "The remaining balance should be 7 after applying a 10% discount to the activity cost.");
    }

    /**
     * Tests the signUpForActivity method for a scenario where the activity cost exceeds
     * the gold passenger's balance. This test ensures that the sign-up is unsuccessful and
     * that the passenger's balance remains unchanged.
     */
    @Test
    void signUpForActivity_Activity_Cost_More_than_balance_unsuccessful() {
        // Create a test activity with a cost higher than the passenger's current balance
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(300.0)
                .description("Test Description")
                .build();

        // Action: Attempt to sign up for the activity, expecting failure due to insufficient balance
        boolean result = goldPassenger.signUpForActivity(activity);

        // Assertions: Confirm that the sign-up was unsuccessful and that the balance is unchanged
        Assertions.assertFalse(result, "Sign up should fail due to insufficient balance.");
        Assertions.assertEquals(250, goldPassenger.getBalance(), "The balance should remain unchanged after a failed sign-up attempt.");
    }
}
