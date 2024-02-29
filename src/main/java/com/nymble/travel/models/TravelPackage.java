package com.nymble.travel.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a travel package, which includes a name, a passenger capacity limit,
 * an itinerary of destinations, and a list of enrolled passengers. This class provides
 * functionalities to add destinations and passengers to the travel package, as well as
 * methods to print the itinerary and the passenger list.
 */
@Data
@Builder
public class TravelPackage {
    private String name; // The name of the travel package
    private int passengerCapacity; // The maximum number of passengers that can be accommodated in this package
    private List<Destination> itinerary = new ArrayList<>(); // A list of destinations included in this travel package
    private List<Passenger> passengers = new ArrayList<>(); // A list of passengers enrolled in this travel package

    /**
     * Adds a new destination to the travel package's itinerary.
     *
     * @param destination The destination to be added.
     */
    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    /**
     * Attempts to add a passenger to the travel package. If the number of already enrolled passengers
     * is less than the passenger capacity, the passenger is added, and the method returns true.
     * Otherwise, the method returns false, indicating no space available for more passengers.
     *
     * @param passenger The passenger to be added to the travel package.
     * @return true if the passenger was successfully added, false otherwise.
     */
    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            return passengers.add(passenger);
        }
        return false;
    }

    /**
     * Prints the itinerary of the travel package, listing each destination along with its activities,
     * including the name, cost, capacity, and description of each activity.
     */
    public void printItinerary() {
        System.out.println("Travel Package: " + this.name);
        for (Destination destination : this.itinerary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("    Activity: " + activity.getName());
                System.out.println("        Cost: " + activity.getCost());
                System.out.println("        Capacity: " + activity.getCapacity());
                System.out.println("        Description: " + activity.getDescription());
            }
        }
    }

    /**
     * Prints a list of passengers enrolled in the travel package, including the passenger
     * capacity of the package, the number of passengers currently enrolled, and the name
     * and number of each passenger.
     */
    public void printPassengerList() {
        System.out.println("Travel Package: " + this.name);
        System.out.println("Passenger Capacity: " + this.passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + this.passengers.size());
        for (Passenger passenger : this.passengers) {
            System.out.println("    Name: " + passenger.getName() + ", Number: " + passenger.getPassengerNumber());
        }
    }
}
