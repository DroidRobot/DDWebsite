package src;
public class Car {
    public String brand;
    public String model;
    public String color;
    // private Account owner;
    private String ownerPhoneNumber;

    // public Car(String brand, String model, String color, Account owner){
    //     this.brand = brand;
    //     this.model = model;
    //     this.color = color;
    //     this.owner = owner;
    // }

    // possible implementation where drivers specify the car owner's phone number for adding to queues

    public Car(String brand, String model, String color, String PhoneNumber){
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.ownerPhoneNumber = PhoneNumber;
    }


    // public Account getCarOwner(){
    //     return owner;
    // }

    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }

    public String getColor(){
        return color;
    }

    public String getOwnerPhoneNumber(){
        return ownerPhoneNumber;
    }
}
