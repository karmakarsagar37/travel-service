package com.nymble.travel.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for the Destination model within the travel package.
 * It ensures that activities can be correctly added to a destination and verifies
 * the integrity of the destination's activities list.
 */
class DestinationTest {
    // Static variables for shared test objects
    public static Activity activity1;
    public static Activity activity2;
    public static Destination destination;

    /**
     * Sets up the test environment before all tests run.
     * Initializes two Activity instances and one Destination instance to be used
     * across all tests. This method is called once before any test method in the class.
     */
    @BeforeAll
    public static void setUp() {
        // Initialize the first test Activity with specified attributes.
        activity1 = Activity.builder()
                .name("Test Name")
                .capacity(1)
                .cost(200.0)
                .description("Test Description")
                .build();

        // Initialize the second test Activity with different attributes.
        activity2 = Activity.builder()
                .name("Test Name 2")
                .capacity(2)
                .cost(200.0)
                .description("Test Description")
                .build();

        // Initialize the test Destination without any activities.
        destination = Destination.builder()
                .name("TestDestination")
                .activities(new ArrayList<>())
                .build();
    }

    /**
     * Tests the addActivity method of the Destination class.
     * This test ensures that activities can be added to a destination's activity list
     * and that the list size is updated accordingly.
     */
    @Test
    public void addActivity() {
        // Add two activities to the destination and assert that the size of the activities list is now 2.
        destination.addActivity(activity1);
        destination.addActivity(activity2);
        Assertions.assertEquals(destination.getActivities().size(), 2, "The destination should have 2 activities after adding them.");
        Assertions.assertEquals(destination.getActivities().get(0), activity1);
        Assertions.assertEquals(destination.getActivities().get(1), activity2);
    }
}
