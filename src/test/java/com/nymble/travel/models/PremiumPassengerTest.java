package com.nymble.travel.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for PremiumPassenger.
 * This class contains unit tests to verify the functionalities of PremiumPassenger,
 * particularly focusing on the signUpForActivity method.
 */
class PremiumPassengerTest {

    // Static instance of PremiumPassenger used for all test cases.
    public static PremiumPassenger premiumPassenger;

    /**
     * Initializes a PremiumPassenger instance before any test runs.
     * This setup method creates a PremiumPassenger with predefined attributes.
     */
    @BeforeAll
    public static void setUp() {
        premiumPassenger = PremiumPassenger.builder()
                .passengerNumber(123456789)
                .name("Test")
                .balance(250)
                .build();
    }

    /**
     * Tests the signUpForActivity method for a successful scenario where a PremiumPassenger
     * signs up for an activity that is free for them.
     * The test verifies that:
     * 1. The signUpForActivity method returns true indicating successful signup.
     * 2. The balance of the PremiumPassenger remains unchanged after signing up for a free activity.
     */
    @Test
    void signUpForActivity_Successful() {
        Activity activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(200.0)
                .description("Test Description")
                .build();
        // Free for "PremiumPassenger", remaining balance shall be 250 - 0 = 250
        boolean result = premiumPassenger.signUpForActivity(activity);

        // Assert: Verify that the signup was successful and balance remains unchanged.
        Assertions.assertTrue(result);
        Assertions.assertEquals(premiumPassenger.getBalance(),250);
    }

}