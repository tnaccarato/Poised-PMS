import java.util.Date;
import java.util.Scanner;

public class PoisedPMS {
    // Calls main method
    public static void main (String [] args) {
    }

    // Creates a new Project object from user's inputs for attributes
    public static Project newProject (String [] args){
    // Takes user inputs for attributes of the project
        Scanner input = new Scanner(System.in);
        System.out.println("What number project is this?");
        int projectNum = input.nextInt();
        System.out.println("What is the name of this project? If this is left blank, " +
                "a name will be generated.");
        String projectName = input.nextLine();
        System.out.println("What type of building is being built?");
        String typeBuilding = input.nextLine();
        System.out.println("What is the address of the building?");
        String address = input.nextLine();
        System.out.println("What is the ERF number of the project?");
        String erfNum = input.nextLine();
        System.out.println("What is the total cost of the project?");
        double cost = input.nextDouble();
        System.out.println("How much has the customer already paid?");
        double totalPaid = input.nextDouble();
        System.out.println("When is the deadline for the project?");
        String deadline = input.nextLine();

        }
    // Creates a new Person object for user's inputs for attributes
    public static Person newPerson (String [] args){
        // Takes user inputs for attributes for the person
        Scanner input = new Scanner(System.in);
        System.out.println("What is this person's role?");
        String role = input.nextLine();
        System.out.println("What is this person's first name?");
        String firstName = input.nextLine();
        System.out.println("What is this person's surname?");
        String surname = input.nextLine();
        System.out.println("What is this person's phone number?");
        String phoneNum = input.nextLine();
        System.out.println("What is this person's email address?");
        String email = input.nextLine();
        System.out.println("What is this person's address?");
        String address = input.nextLine();


    }
}

