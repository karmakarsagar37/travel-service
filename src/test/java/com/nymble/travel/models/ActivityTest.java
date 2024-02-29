package com.nymble.travel.models;

import org.junit.jupiter.api.*;

/**
 * Tests for the {@link Activity} class to ensure that passenger management behaves as expected.
 * This includes adding passengers to an activity, and handling cases where the activity is at full capacity.
 */
class ActivityTest {
    private static Activity activity;

    /**
     * Sets up a shared Activity instance for all test methods.
     * This method runs once before all the test methods in the class.
     */
    @BeforeAll
    public static void setUp() {
        // Initialize a test Activity instance with specified capacity, cost, and description.
        activity = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(200.0)
                .description("Test Description")
                .build();
    }

    /**
     * Test to verify that adding a passenger to the activity succeeds when there is enough capacity.
     * This test ensures that the method correctly updates the enrolled passengers count and returns true.
     */
    @Test
    public void addPassenger_successfully_added() {
        // Attempt to add a passenger and assert that the operation was successful and the enrolled passenger count is updated.
        boolean actualResult = activity.addPassenger(StandardPassenger.builder()
                .passengerNumber(1)
                .balance(200)
                .name("TestName")
                .build());
        Assertions.assertEquals(1, activity.getEnrolledPassengers(), "The enrolled passengers count should be updated to 1.");
        Assertions.assertTrue(actualResult, "The passenger should be successfully added.");
    }

    /**
     * Test to verify that adding a passenger fails when the activity is already at full capacity.
     * This test checks that the method does not modify the enrolled passengers count and returns false.
     */
    @Test
    public void addPassenger_test_capacity_full_passenger_not_added() {
        // Attempt to add another passenger and assert that the operation fails without changing the enrolled passenger count.
        boolean actualResult = activity.addPassenger(StandardPassenger.builder()
                .passengerNumber(2)
                .balance(200)
                .name("TestName2")
                .build());

        Assertions.assertEquals(1, activity.getEnrolledPassengers(), "The enrolled passengers count should remain unchanged.");
        Assertions.assertFalse(actualResult, "The passenger should not be added due to full capacity.");
    }
}
