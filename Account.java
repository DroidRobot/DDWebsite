import java.util.PriorityQueue;

public class Account {
    private String name;
    private String accountType; // true driver false passenger
    // private PQueue<Account> queue;
    private int queueSize;

// public Account() {
// name = "";
// accountType = false;
// // queue = new PQueue<Account>();
// // queueSize = queue.size();
// }

// public Account(String name, boolean accountType){
// this.name = name;
// this.accountType = accountType;
// queue = new PQueue<Account>();
// queueSize = queue.size();
// }
    public Account(String name, boolean type) {
        this.name = name;
        if (type != true) {
            this.accountType = "Driver";
        }
        else {
            this.accountType = "user";
        }
    }


    public String getName() {
        return name;
    }


    public String getAccountType() {
        return accountType;
    }
// public PQueue<Account> getQueue(){
// // return queue;
// }


    public void setName(String newName) {
        name = newName;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append(" Account Type: " + accountType);
        return sb.toString();
    }

}
