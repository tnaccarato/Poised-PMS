import java.util.Scanner;

public class Person {
    // Attributes
    String role;
    String firstName;
    String surname;
    String phoneNum;
    String email;
    String address;

    // Methods

    // Constructor
    public Person (String role, String firstName, String surname, String phoneNum, String email,
                   String address){
        this.role = role;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
    }

    // toString
    public String toString (){
        String output = "\nRole: " + role;
        output += "\nName: " + firstName + " " + surname;
        output += "\nPhone Number: " + phoneNum;
        output += "\nEmail: " + email;
        output += "\nAddress: " + address;
        return output;
    }

    // Update Person details
    public static Person changeDetails(Person activePerson){
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
        if ("n".equals(changeDetailsInput)){
            System.out.println("What would you like to change their first name to?");
            String newFirstName = changeDetailsScanner.nextLine();
            System.out.println("What would you like to change their surname to?");
            String newSurname = changeDetailsScanner.nextLine();
            activePerson.firstName = newFirstName;
            activePerson.surname = newSurname;
            return activePerson;
        }
        else if ("p".equals(changeDetailsInput)){
            System.out.println("What would you like to change their phone number to?");
            String newPhoneNum = changeDetailsScanner.nextLine();
            activePerson.phoneNum = newPhoneNum;
            return activePerson;
        }
        else if ("e".equals(changeDetailsInput)){
            System.out.println("What would you like to change their email address to?");
            String newEmail = changeDetailsScanner.nextLine();
            activePerson.email = newEmail;
            return activePerson;
        }
        else if ("a".equals(changeDetailsInput)){
            System.out.println("What would you like to change their address to");
            String newAddress = changeDetailsScanner.nextLine();
            activePerson.address = newAddress;
            return activePerson;
        }
        else{
            System.out.println("Sorry, your choice \"" + changeDetailsInput +
                    "\" was not recognised," + "please try again.");
            return activePerson;
        }
    }
}
