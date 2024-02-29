package com.nymble.travel.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TravelPackageTest {

    private TravelPackage travelPackage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture output for testing print methods
        System.setOut(new PrintStream(outContent));

        travelPackage = TravelPackage.builder()
                .name("Holiday Special")
                .passengerCapacity(2)
                .itinerary(new ArrayList<>())
                .passengers(new ArrayList<>())
                .build();

        Destination destination = Destination.builder()
                .name("Delhi")
                .activities(List.of(
                        Activity.builder().name("Red Fort Visit").cost(100.0).capacity(4).description("Visit the iconic fort").build(),
                        Activity.builder().name("Parliament House Tour").cost(120.0).capacity(5).description("Explore democratic building").build()
                ))
                .build();

        travelPackage.addDestination(destination);
    }

    @Test
    void addPassenger_Success() {
        Passenger passenger1 = Mockito.mock(Passenger.class);
        boolean result = travelPackage.addPassenger(passenger1);
        assertTrue(result, "Passenger should be added successfully");
        assertEquals(1, travelPackage.getPassengers().size(), "There should be one passenger in the list");
    }

    @Test
    void addPassenger_Failure() {
        Passenger passenger1 = Mockito.mock(Passenger.class);
        Passenger passenger2 = Mockito.mock(Passenger.class);
        Passenger passenger3 = Mockito.mock(Passenger.class);

        travelPackage.addPassenger(passenger1); // 1st passenger
        travelPackage.addPassenger(passenger2); // 2nd passenger

        boolean result = travelPackage.addPassenger(passenger3); // 3rd passenger, should fail
        assertFalse(result, "No more passengers should be added as capacity is reached");
        assertEquals(2, travelPackage.getPassengers().size(), "There should be two passengers in the list");
    }

    @Test
    void printItinerary_Output() {
        travelPackage.printItinerary();
        String expectedOutput = "Travel Package: Holiday Special\n" +
                "Destination: Delhi\n" +
                "    Activity: Red Fort Visit\n" +
                "        Cost: 100.0\n" +
                "        Capacity: 4\n" +
                "        Description: Visit the iconic fort\n" +
                "    Activity: Parliament House Tour\n" +
                "        Cost: 120.0\n" +
                "        Capacity: 5\n" +
                "        Description: Explore democratic building\n";

        assertEquals(expectedOutput, outContent.toString(), "Itinerary output does not match expected output");
    }

    @Test
    void printPassengerList_Output() {
        Passenger passenger1 = StandardPassenger.builder().name("Test Name").passengerNumber(987654321).balance(200).build();
        Passenger passenger2 = PremiumPassenger.builder().name("Test Name").passengerNumber(123456789).balance(200).build();
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);

        travelPackage.printPassengerList();
        String expectedOutput = "Travel Package: Holiday Special\n" +
                "Passenger Capacity: 2\n" +
                "Number of Passengers Enrolled: 2\n" +
                "    Name: Test Name, Number: 987654321\n" +
                "    Name: Test Name, Number: 123456789\n";

        assertEquals(expectedOutput, outContent.toString(), "Passenger list output does not match expected output");
    }
}
