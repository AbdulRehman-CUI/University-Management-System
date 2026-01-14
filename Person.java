import java.io.Serializable;

public abstract class Person implements Serializable {
    // Attributes
    protected String name;
    protected String id;
    protected String email;
    protected int phoneNumber;
    protected String address;
    protected String role;
    protected boolean isActive;
    private String password;


    // Constructor
    public Person(String name, String id, String email, int phoneNumber,String address, String role, String password){
        this.name = name;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.password = password;
        this.isActive = true;
    }

    // Getters and Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getId(){return id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public int getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    // 1. Login Method
    public boolean login(String inputID, String inputPass){
        if(!this.isActive){
            System.out.println("Account is Deactivated!!!");
            return false;
        }
        if(this.id.equals(inputID) && this.password.equals(inputPass)){
            System.out.println("Welcome " + this.getName());
            return true;
        }
        return false;
    }

    // 2. Contact Info Management Method
    public void manageProfile(String newEmail, int newPhoneNumber){
        this.email = newEmail;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Contact Information Updated for " + getId());
    }

    // 3. Update Password Method
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            if (newPassword.length() >= 8) {
                this.password = newPassword;
                System.out.println("Success: Password updated.");
                return true;
            }
            else {
                System.out.println("The length of the password should be of atleat 8 characters!");
                return false;
            }
        }
        else {
            System.out.println("You have entered the wrong password.");
            return false;
        }
    }

    // 4. Updating the status
    protected void setStatus(boolean newStatus){
        this.isActive = newStatus;
        System.out.println("Status updated successfully...");
    }

    // 5. Abstract Method
    public abstract void displayDashboard();
}
