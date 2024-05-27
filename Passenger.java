public class Passenger extends Account{
    // private Location location; // to be implemented using location api's
    private int positionInQueue;
    private Location location;
    private boolean inQueue;



    public Passenger(String Name, String PhoneNumber, Location location) {
        super(Name, false, PhoneNumber);
        this.location = location;
        inQueue = false;
    }

    // if the passenger is not already in the driver's queue and they haven't joined another person's queue, they can join the queue.
    public void joinQueue(Driver driver){
        if (!driver.getQueue().contains(this) && !inQueue){
            driver.addToQueue(this);
            positionInQueue = driver.getQueue().indexOf(this);
            inQueue = true;
        }
        
    }

    public void leaveQueue(Driver driver){
        driver.removeFromQueue(this);
        positionInQueue = 0;
        inQueue = false;
    }

    public void updateQueuePosition(int position){
        this.positionInQueue = position;
    }

    // returns the estimated time to pickup 
    public int timeToPickup(){
        // TODO - 
    }

    // ideally it returns passengers location, so Driver can see them in real time
    public Location getLocation(){
        return this.location;
    }
    
    // returns the location of the driver who's queue they are in only if they are in that driver's queue.
    public Location getCarLocation(Driver driver){
        if (driver.getQueue().contains(this)){
            return driver.getLocation();
        }
    }

    // returns the carInfo of the driver's car
    public String getCarInfo(Driver driver){
        return driver.carInfo();
    }
}
 
