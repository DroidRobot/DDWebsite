public class Driver extends Account{
        // ~ Fields ................................................................
    PQueue<Account> Queue;

    // ~ Constructors ..........................................................
    Driver(String Name, boolean Type) {
        super(Name, Type);
        Queue = new PQueue<Account>();
    }


    // ~Public Methods ........................................................
    // returns the current queue of an active drive
    public int getQueue() {
        return Queue.size();
    }


    // adds a userAccount to the queue
    public void addToQueue(UserAccount user) {
        Queue.add(user);
    }


    // removes a userAccount from queue
    public void removeFromQueue() {
        Queue.remove();
    }

}
