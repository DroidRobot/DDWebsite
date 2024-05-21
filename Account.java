import java.util.PriorityQueue;

public class Account {
    private String name;
    private boolean accountType; // true driver false passenger
    private PQueue<Account> queue;
    private int queueSize;
    public Account(){
        name = "";
        accountType = false;
        queue = new PQueue<Account>();
        queueSize = queue.size();
    }
    public Account(String name, boolean accountType){
        this.name = name;
        this.accountType = accountType;
        queue = new PQueue<Account>();
        queueSize = queue.size();
    }
    public String getName(){
        return name;
    }
    public boolean getAccountType(){
        return accountType;
    }
    public PQueue<Account> getQueue(){
        return queue;
    }

    public void setName(String newName){
        name = newName;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        if(accountType){
            sb.append(" Account Type: Driver");
            sb.append(" Queue: ");
            sb.append(queue.size());
        }else{
            sb.append(" Account Type: Passenger");
        }
        return sb.toString();
    }
}
