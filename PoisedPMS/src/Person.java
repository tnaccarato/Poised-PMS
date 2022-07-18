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
            activePerson.firstName = newFirstName;
            activePerson.surname = newSurname;
            // Prints a confirmation
            System.out.println("Name changed successfully.");
        }
        // If user enters p, allow them to change person's phone number
        else if ("p".equals(changeDetailsInput)){
            System.out.println("What would you like to change their phone number to?");
            String newPhoneNum = changeDetailsScanner.nextLine();
            activePerson.phoneNum = newPhoneNum;
            System.out.println("Phone number changed successfully.");
        }
        // If user enters e, allows them to change person's email
        else if ("e".equals(changeDetailsInput)){
            System.out.println("What would you like to change their email address to?");
            String newEmail = changeDetailsScanner.nextLine();
            activePerson.email = newEmail;
            // Prints a confirmation
            System.out.println("Email changed successfully.");
        }
        // If user enters a, allows them to change person's address
        else if ("a".equals(changeDetailsInput)){
            System.out.println("What would you like to change their address to");
            String newAddress = changeDetailsScanner.nextLine();
            activePerson.address = newAddress;
            // Prints a confirmation
            System.out.println("Address changed successfully.");
        }
        else{
            System.out.println("Sorry, your choice \"" + changeDetailsInput +
                    "\" was not recognised," + "please try again.");
        }
    }
}
