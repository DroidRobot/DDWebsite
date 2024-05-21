
public class Account {
    private String name;
    private boolean accountType; // true driver false passenger
    public Account(){
        name = "";
        accountType = false;
    }
    public Account(String name, boolean accountType){
        this.name = name;
        this.accountType = accountType;
    }
    public String getName(){
        return name;
    }
    public String getAccount(){
        if(accountType){
            return "Driver";
        }
        return "Passenger";
    }
    public boolean getAccountBool(){
        return accountType;
    }
}
