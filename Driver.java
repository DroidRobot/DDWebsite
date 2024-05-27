import java.util.Random;

public class Driver extends Account{
        // ~ Fields ................................................................
    private PQueue<Passenger> Queue;
    public int queueSize;
    public String status = "Not Driving";
    private Car car;
    private Location location;
    // Location = currentLocation; // to be implemented with ios/android location api


    // ~ Constructors ..........................................................
    public Driver(String Name, String PhoneNumber, Location location, Car car) {
        super(Name, true, PhoneNumber);
        Queue = new PQueue<Passenger>();
        this.location = location;
        this.car = car;
    }
    
    // driver breaks: 20 mins/6 hours
    // if less than 1 of the current drivers are on break, then they can go on break (Pee breaks, getting gas, etc.)
    private void setStatus(String status){
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
        sb.append(car.getColor() + " ");
        sb.append(car.getBrand() + " ");
        sb.append(car.getModel());
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


    // adds a userAccount to the queue.
    public void addToQueue(Account user) {
        if (!this.status.equals("Driving")){
            return;
        }
        // if the passengers phone number matches driver phone number, 
        // return without adding to queue.
        if (this.getPhoneNumber().equals(user.getPhoneNumber())){
            return;
        }
        // If the Driver's car is tied to another Brother's 'Account' and that brother joins the queue, add brother to front of queue.
        if (this.car.getCarOwner().equals(user)){
            Queue.priorityAdd(user);
        }
        Queue.add(user);
        updatePassengerQueuePositions();
    }


    // removes first user from queue once driver is on the way to the user. 
    // This avoids issues when people priorityQueue when already en route to next user in queue.
    public void removeFromQueue() {
        Queue.remove();
        updatePassengerQueuePositions();
    }

    // removes specific user from queue. Generally for when people cancel rides
    public void removeFromQueue(Account user){
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

    // gets the passenger's location
    public Location getPassengerLocation(int position){
        // TODO Auto-generated method stub        
    }


    // gets the estimated time to pickup for user
    public int timeToQueue(Account user){
        // to be implemented using location api's
        return time;
    }

    // Shows the driver how long it will take to finish the current queue
    private int timeToFinishQueue(){
        // TODO Auto-generated method stub        
        return time;
    }

    // Returns the time it will take to get to a specific spot in the queue
    private int timeToSpecificQueue(int position){
        // TODO Auto-generated method stub        
        return time;
    }
}
