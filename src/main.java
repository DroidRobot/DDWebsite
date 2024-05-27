package src;

import java.sql.DriverManager;

public class main{
    public static void main(String[] args){
         // Create a location for driver and passenger
         Location driverLocation = new Location(40.7128, -74.0060); // Example coordinates for New York City
         Location passengerLocation = new Location(40.7306, -73.9352); // Example coordinates for Brooklyn, NYC
 
         // Create a car for the driver
         Car car = new Car("Grey", "Honda", "Civic", "123456"); // Example car details
 
         // Create a driver
         Driver driver = new Driver("John Doe", "1234567890", driverLocation, car, System.currentTimeMillis());
         driver.setStatus("Driving");
 
         // Create a passenger
         Passenger passenger = new Passenger("Jane Smith", "9876543210", passengerLocation);
         Passenger passenger2 = new Passenger("Skibidy Smith", "9876543210", passengerLocation);
         Passenger passenger3 = new Passenger("Jane Mogging", "9876543210", passengerLocation);
 
         // Demonstrate methods in the Driver class
         System.out.println("Driver Name: " + driver.getName());
         System.out.println("Driver Phone Number: " + driver.getPhoneNumber());
         System.out.println("Driver Car Info: " + driver.carInfo());
         System.out.println("Driver Current Location: " + driver.getLocation());
 
         // Demonstrate methods in the Passenger class
         System.out.println("Passenger Name: " + passenger.getName());
         System.out.println("Passenger Phone Number: " + passenger.getPhoneNumber());
         System.out.println("Passenger Current Location: " + passenger.getLocation());
 
         // Demonstrate interaction between Driver and Passenger
         System.out.println("Passenger joining Driver's queue...");
         passenger.joinQueue(driver, passengerLocation, driverLocation, 0);
         passenger2.joinQueue(driver, passengerLocation, driverLocation, 0);
         passenger3.joinQueue(driver, passengerLocation, driverLocation, 0);
         driver.removeFromQueue();

         System.out.println("Passenger's current trip: " + passenger.getCurrentTrip());
 
         // Show the Driver's queue
         System.out.println("Driver's Queue: " + driver.getQueueSize());
 
         // Simulate the driver arriving at pickup and destination
         driver.arrivedAtPickup();
         driver.arrivedAtDestination();
 
         // Demonstrate updating positions in the passenger queue
         driver.updatePassengerQueuePositions();
         System.out.println("Passenger's position in queue: " + passenger.getPositionInQueue());
 
         // Demonstrate leaving the queue
         System.out.println("Passenger leaving the queue...");
         passenger.leaveQueue();

         System.out.println("Driver's Queue: " + driver.getQueueSize());
        
    }
}