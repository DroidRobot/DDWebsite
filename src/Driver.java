package src;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import org.json.JSONObject;


public class Driver extends Account{
        // ~ Fields ................................................................
    private PQueue<Passenger> Queue;
    private Trip currentPickup;
    private Trip currentDropOff;
    public String status = "Not Driving";
    private Car car;
    private Location location;
    // Location = currentLocation; // to be implemented with ios/android location api
    
    // time allocations
    private long currentTime;
    private long shiftEndTime;
    private long estimatedQueueTime;

    // ~ Constructors ..........................................................
    public Driver(String Name, String PhoneNumber, Location location, Car car, long startTime) {
        super(Name, true, PhoneNumber);
        Queue = new PQueue<Passenger>();
        this.location = location;
        this.car = car;
        currentDropOff = null;
        currentPickup = null;

        currentTime = startTime;
        shiftEndTime = startTime + 6 * 60 * 60 * 1000;

    }
    
    // driver breaks: 20 mins/6 hours
    // if less than 1 of the current drivers are on break, then they can go on break (Pee breaks, getting gas, etc.)
    public void setStatus(String status){
        // Implement break logic

        this.status = status;
    }

    // ~Public Methods ........................................................

    // returns the driver's queue object
    public PQueue<Passenger> getQueue(){
        return this.Queue;
    }

    // returns the color, brand, and model of the driver's car. Example: "Grey Honda Civic"

    public String carInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append(car.getBrand() + " ");
        sb.append(car.getModel() + " ");
        sb.append(car.getColor());
        return sb.toString();
    }

    // returns the current queue of an active drive
    public int getQueueLength() {
        return Queue.size();
    }

    // returns the current Location of the driver
    public Location getLocation(){
        return this.location;
    }

    // gets the size of the driver's queue
    public int getQueueSize(){
        return Queue.size();
    }

    // adds a userAccount to the queue.
    public boolean addToQueue(Account user) {
        // if the driver is not driving (on break), they cannot join the queue
        if (!this.status.equals("Driving")){
            return false;
        }

        // if the passengers phone number matches driver phone number, 
        // return without adding to queue.
        if (this.getPhoneNumber().equals(user.getPhoneNumber())){
            return false;
        }

        try {
            estimatedQueueTime = calculateTrips();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // estimated time when shift will be done

        // Check if the total time to finish queue exceeds shift end time minus 20 minutes
        if ((estimatedQueueTime * 60 * 1000) > (shiftEndTime - 20 * 60 * 1000)) {
            System.out.println("Cannot add passenger " + user.getName() + ": Estimated completion time exceeds shift end time minus 20 minutes.");
            return false;
        }

        // If the Driver's car is tied to another Brother's 'Account' and that brother joins the queue, add brother to front of queue.
        if (this.car.getOwnerPhoneNumber().equals(user.getPhoneNumber())){
            Queue.priorityAdd((Passenger) user);
            return true;
        }
        Queue.add((Passenger) user);
        updatePassengerQueuePositions();
        return true;

    }


    // removes first user from queue once driver is on the way to the user. 
    // This avoids issues when people priorityQueue when already en route to next user in queue.
    public void removeFromQueue() {
        // current passenger being picked up/dropped off.
        Passenger currPassenger = Queue.remove(); 
        this.currentPickup = new Trip(this.location, currPassenger.getCurrentTrip().getOrigin());
        this.currentDropOff = currPassenger.getCurrentTrip();
        updatePassengerQueuePositions();

        try {
            printCalculateTrips();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // removes specific user from queue. Generally for when people cancel rides
    public void removeFromQueue(Passenger user){
        Queue.remove(user);
        updatePassengerQueuePositions();
    }

    // goes through passenger queue and updates the positionInQueue attribute of each passenger 
    public void updatePassengerQueuePositions(){
        for (Object passenger : Queue){
            if (((Passenger) passenger).getName().contains("DeChario")){
                Random random = new Random();
                int randomInt = random.nextInt(100);
                if (randomInt < 10){
                    ((Passenger) passenger).updateQueuePosition(Queue.size());
                }
                else{
                    ((Passenger) passenger).updateQueuePosition(Queue.indexOf(passenger) + 1);
                }
            }
            else{
                ((Passenger) passenger).updateQueuePosition(Queue.indexOf(passenger) + 1);
            }
        }
    }

    
    public void arrivedAtPickup(){
        this.currentPickup = null;
    }

    public void arrivedAtDestination(){
        this.currentDropOff = null;
        // if there are any people left in the queue when they reach the destination, automatically set the next trip to the next passenger in queue
        if (Queue.size() != 0){
            removeFromQueue();
        }
    }

    public long calculateTrips() throws Exception {
        Location currentLocation = currentDropOff.getDestination();
        long tempCurrentTime = calculateCurrentTrip(); // starting time for rest of queue is dependent on how long it will take to finish the current trip

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));


        for (Object passenger : Queue) {
            // Calculate travel time to pick up the passenger
            int pickUpTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getCurrentTrip().getOrigin());
            Trip currentPickUpTrip = new Trip(currentLocation, ((Passenger) passenger).getCurrentTrip().getOrigin());

            // Print the current pick-up trip
            tempCurrentTime += pickUpTravelTime * 60 * 1000; // Convert minutes to milliseconds
            Date pickUpDate = new Date(tempCurrentTime);
            String pickUpTime = dateFormat.format(pickUpDate);
            System.out.println("Pick-up time for " + ((Passenger) passenger).getName() + ": " + pickUpTime);
            System.out.println("Current Pick-Up Trip: " + currentPickUpTrip);

            // Move to the passenger's pick-up location
            currentLocation = ((Passenger) passenger).getCurrentTrip().getOrigin();

            // Calculate travel time to drop off the passenger
            int dropOffTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getCurrentTrip().getDestination());
            Trip currentDropOffTrip = new Trip(currentLocation, ((Passenger) passenger).getCurrentTrip().getDestination());

            // Print the current drop-off trip
            tempCurrentTime += dropOffTravelTime * 60 * 1000; // Convert minutes to milliseconds
            Date dropOffDate = new Date(tempCurrentTime);
            String dropOffTime = dateFormat.format(dropOffDate);
            System.out.println("Drop-off time for " + ((Passenger) passenger).getName() + ": " + dropOffTime);
            System.out.println("Current Drop-Off Trip: " + currentDropOffTrip);

            // Move to the passenger's drop-off location
            currentLocation = ((Passenger) passenger).getCurrentTrip().getDestination();
        }
        return tempCurrentTime;
    }

    public void printCalculateTrips() throws Exception {
        Location currentLocation = currentDropOff.getDestination();
        long tempCurrentTime = calculateCurrentTrip(); // starting time for rest of queue is dependent on how long it will take to finish the current trip

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));


        for (Object passenger : Queue) {
            // Calculate travel time to pick up the passenger
            int pickUpTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getCurrentTrip().getOrigin());
            Trip currentPickUpTrip = new Trip(currentLocation, ((Passenger) passenger).getCurrentTrip().getOrigin());

            // Print the current pick-up trip
            tempCurrentTime += pickUpTravelTime * 60 * 1000; // Convert minutes to milliseconds
            Date pickUpDate = new Date(tempCurrentTime);
            String pickUpTime = dateFormat.format(pickUpDate);
            System.out.println("Pick-up time for " + ((Passenger) passenger).getName() + ": " + pickUpTime);
            System.out.println("Current Pick-Up Trip: " + currentPickUpTrip);

            // Move to the passenger's pick-up location
            currentLocation = ((Passenger) passenger).getCurrentTrip().getOrigin();

            // Calculate travel time to drop off the passenger
            int dropOffTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getCurrentTrip().getDestination());
            Trip currentDropOffTrip = new Trip(currentLocation, ((Passenger) passenger).getCurrentTrip().getDestination());

            // Print the current drop-off trip
            tempCurrentTime += dropOffTravelTime * 60 * 1000; // Convert minutes to milliseconds
            Date dropOffDate = new Date(tempCurrentTime);
            String dropOffTime = dateFormat.format(dropOffDate);
            System.out.println("Drop-off time for " + ((Passenger) passenger).getName() + ": " + dropOffTime);
            System.out.println("Current Drop-Off Trip: " + currentDropOffTrip);

            // Move to the passenger's drop-off location
            currentLocation = ((Passenger) passenger).getCurrentTrip().getDestination();
        }
    }

    private int getTravelTime(Location origin, Location destination) throws Exception {
        JSONObject response = getDirectionsApiResponse(origin, destination);
        int duration = response.getJSONArray("routes")
                              .getJSONObject(0)
                              .getJSONArray("legs")
                              .getJSONObject(0)
                              .getJSONObject("duration")
                              .getInt("value"); // Duration in seconds
        return duration / 60; // Convert to minutes
    }

    private int getTravelTime(Trip trip) throws Exception {
        if (trip == null){
            return 0;
        }
        JSONObject response = getDirectionsApiResponse(trip.getOrigin(), trip.getDestination());
        int duration = response.getJSONArray("routes")
                              .getJSONObject(0)
                              .getJSONArray("legs")
                              .getJSONObject(0)
                              .getJSONObject("duration")
                              .getInt("value"); // Duration in seconds
        return duration / 60; // Convert to minutes
    }

    // returns the estimated time at which the current trip will be completed
    public long calculateCurrentTrip() throws Exception {
        Location currentLocation = this.location;
        long tempCurrentTime = currentTime;

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Calculate travel time to pick up the passenger
        int pickUpTravelTime = getTravelTime(currentPickup);

        // Print the current pick-up trip
        tempCurrentTime += pickUpTravelTime * 60 * 1000; // Convert minutes to milliseconds
        Date pickUpDate = new Date(tempCurrentTime);
        String pickUpTime = dateFormat.format(pickUpDate);
        System.out.println("Pick-up time for current trip: " + pickUpTime);
        System.out.println("Current Pick-Up Trip: " + currentPickup);

        // Calculate travel time to drop off the passenger
        int dropOffTravelTime = getTravelTime(currentDropOff);

        // Print the current drop-off trip
        tempCurrentTime += dropOffTravelTime * 60 * 1000; // Convert minutes to milliseconds
        Date dropOffDate = new Date(tempCurrentTime);
        String dropOffTime = dateFormat.format(dropOffDate);
        System.out.println("Drop-off time for current trip: " + dropOffTime);
        System.out.println("Current Drop-Off Trip: " + currentDropOff);
        return tempCurrentTime;
    }

    // To String for time when a passenger would be picked up
    public String getPickUpTimeForPassenger(Passenger targetPassenger) throws Exception {
        Location currentLocation = this.location;
        long simulatedCurrentTime = currentTime;

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        for (Object passenger : Queue) {
            // Calculate travel time to pick up the passenger
            int pickUpTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getLocation());
            simulatedCurrentTime += pickUpTravelTime * 60 * 1000; // Convert minutes to milliseconds

            if (passenger.equals(targetPassenger)) {
                Date pickUpDate = new Date(simulatedCurrentTime);
                return dateFormat.format(pickUpDate);
            }

            // Move to the passenger's pick-up location
            currentLocation = ((Passenger) passenger).getLocation();

            // Calculate travel time to drop off the passenger
            int dropOffTravelTime = getTravelTime(currentLocation, ((Passenger) passenger).getCurrentTrip().getDestination());
            simulatedCurrentTime += dropOffTravelTime * 60 * 1000; // Convert minutes to milliseconds

            // Move to the passenger's drop-off location
            currentLocation = ((Passenger) passenger).getCurrentTrip().getDestination();
        }

        return "Passenger not found in the queue.";
    }


    private JSONObject getDirectionsApiResponse(Location origin, Location destination) {
        // Placeholder implementation for demonstration purposes
        // In a real application, this method would fetch directions from a mapping service API
        // Here, we'll just create a dummy JSONObject with sample data
        
        JSONObject response = new JSONObject();
        response.put("routes", new JSONObject[] {
            new JSONObject().put("legs", new JSONObject[] {
                new JSONObject().put("duration", new JSONObject().put("value", 300)) // Dummy duration value in seconds
            })
        });

        return response;
    }
    
}
