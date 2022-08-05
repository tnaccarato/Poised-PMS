import java.util.Scanner;

public class Person {
    // Attributes
    private String role;
    private String firstName;
    private String surname;
    private String phoneNum;
    private String email;
    private String address;

    // Methods

    // Constructor
    public Person (String role, String firstName, String surname, String phoneNum, String email,
                   String address){
        this.setRole(role);
        this.setFirstName(firstName);
        this.setSurname(surname);
        this.setPhoneNum(phoneNum);
        this.setEmail(email);
        this.setAddress(address);
    }

    // toString
    public String toString (){
        String output = "\nRole: " + getRole();
        output += "\nName: " + getFirstName() + " " + getSurname();
        output += "\nPhone Number: " + getPhoneNum();
        output += "\nEmail: " + getEmail();
        output += "\nAddress: " + getAddress();
        return output;
    }

    // Update Person details
    public static void changeDetails(Person activePerson){
        // Asks which details the user wants to change
        Scanner changeDetailsScanner = new Scanner(System.in);
        System.out.println("""
    Which details would you like to change?
    n - Name
    p - Phone number
    e - Email Address
    a - Address
    """);
        String changeDetailsInput = changeDetailsScanner.nextLine();
        // If user enters n, allow them to change the person's name
        if ("n".equals(changeDetailsInput)){
            System.out.println("What would you like to change their first name to?");
            String newFirstName = changeDetailsScanner.nextLine();
            System.out.println("What would you like to change their surname to?");
            String newSurname = changeDetailsScanner.nextLine();
            activePerson.setFirstName(newFirstName);
            activePerson.setSurname(newSurname);
            // Prints a confirmation
            System.out.println("Name changed successfully.");
        }
        // If user enters p, allow them to change person's phone number
        else if ("p".equals(changeDetailsInput)){
            System.out.println("What would you like to change their phone number to?");
            String newPhoneNum = changeDetailsScanner.nextLine();
            activePerson.setPhoneNum(newPhoneNum);
            System.out.println("Phone number changed successfully.");
        }
        // If user enters e, allows them to change person's email
        else if ("e".equals(changeDetailsInput)){
            System.out.println("What would you like to change their email address to?");
            String newEmail = changeDetailsScanner.nextLine();
            activePerson.setEmail(newEmail);
            // Prints a confirmation
            System.out.println("Email changed successfully.");
        }
        // If user enters a, allows them to change person's address
        else if ("a".equals(changeDetailsInput)){
            System.out.println("What would you like to change their address to");
            String newAddress = changeDetailsScanner.nextLine();
            activePerson.setAddress(newAddress);
            // Prints a confirmation
            System.out.println("Address changed successfully.");
        }
        else{
            System.out.println("Sorry, your choice \"" + changeDetailsInput +
                    "\" was not recognised," + "please try again.");
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
