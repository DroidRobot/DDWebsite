package src;

public class Account {
    private String name;
    private String accountType; // true driver false passenger
    private String phoneNumber;

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
    public Account(String name, boolean driver, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.setAccountType(driver);
    }


    public String getName() {
        return name;
    }

    
    public String getPhoneNumber(){
        return this.phoneNumber;
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

    public void setPhoneNumber(String newPhoneNumber){
        this.phoneNumber = newPhoneNumber;
    }

    public void setAccountType(boolean driver){
        if (driver) {
            this.accountType = "Driver";
        }
        else {
            this.accountType = "user";
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append(" Account Type: " + accountType);
        sb.append(" Phone Number: " + phoneNumber);
        return sb.toString();
    }

    public boolean equals(Account account){
        return this.phoneNumber.equals(account.getPhoneNumber());
    }

}
