package src;
public class Trip {
    public Location origin;
    public Location destination;
    public int numStops;
    public int estimatedTime;


    // trip constructor for passenger's current trip.
    public Trip (Location origin, Location destination, int numStops){
        this.origin = origin;
        this.destination = destination;
        this.numStops = numStops;
        this.estimatedTime = 15 + (numStops * 15);
    }

    // trip constructor for driver going to first pickup
    public Trip (Location origin, Location destination){
        this.origin = origin;
        this.destination = destination;
        this.numStops = 1;
        this.estimatedTime = 15;
    }

    public Location getDestination(){
        return destination;
    }

    public Location getOrigin(){
        return origin;
    }

    public int getNumStops(){
        return numStops;
    }

    public int getMinutesToFirstArrival(){
        return 15;
    }

    public int getEstimatedTripTime(){
        return estimatedTime;
    }
}
