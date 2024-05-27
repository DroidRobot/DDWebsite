package src;
public class Passenger extends Account{
    // private Location location; // to be implemented using location api's
    private int positionInQueue;
    private Location location;
    private boolean inQueue;
    private Trip currentTrip;
    private Driver currentDriver;

    public Passenger(String Name, String PhoneNumber, Location location) {
        super(Name, false, PhoneNumber);
        this.location = location;
        inQueue = false;
    }
    
    // ideally it returns passengers location, so Driver can see them in real time
    public Location getLocation(){
        return this.location;
    }

    public Trip getCurrentTrip(){
        return currentTrip;
    }
    
    public void updateQueuePosition(int position){
        this.positionInQueue = position;
    }

     // returns the carInfo of the driver's car
     public String getCarInfo(Driver driver){
        return driver.carInfo();
    }

    public int getPositionInQueue(){
        return this.positionInQueue;
    }

    // if the passenger is not already in the driver's queue and they haven't joined another person's queue, they can join the queue.
    public void joinQueue(Driver driver, Location origin, Location destination, int numStops){
        if (!driver.getQueue().contains(this) && !inQueue){
            this.currentTrip = new Trip(origin, destination, numStops);
            driver.addToQueue(this);
            positionInQueue = driver.getQueue().indexOf(this);
            inQueue = true;
            currentDriver = driver;
        }
        
    }

    public void leaveQueue(){
        if (currentDriver != null){
            currentDriver.removeFromQueue(this);
            positionInQueue = 0;
            inQueue = false;
            currentTrip = null;
            currentDriver = null;
        }
    }

    // returns the location of the driver who's queue they are in only if they are in that driver's queue.
    public Location getCarLocation(Driver driver){
        if (currentDriver != null && currentDriver.getQueue().contains(this)){
            return driver.getLocation();
        }
        return null;
    }

   
}
 
