import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PoisedPMS {
    // Creates an empty project list (1)
    static List<Project> projectList = new LinkedList<>();
    // Calls main method
    public static void main (String [] args){
        newProject();
        System.out.println(projectList);
    }

    // Creates a new Project object from user's inputs for attributes and adds it to a list
    public static void newProject (){
    // Takes user inputs for attributes of the project
        Scanner input = new Scanner(System.in);
        System.out.println("What number project is this?");
        int projectNum = input.nextInt();
        input.nextLine();   // Consumes rest of line so scanner works correctly (2)
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
        input.nextLine();
        System.out.println("How much has the customer already paid?");
        double totalPaid = input.nextDouble();
        input.nextLine();
        System.out.println("When is the deadline for the project? Please enter as dd/MM/yyyy " +
                "(i.e. 10/12/2022");
        String deadlineS = input.nextLine();
        // Converts deadline into Date (3)
        LocalDate deadline = LocalDate.parse(deadlineS);
        // Defaults finalised to false
        boolean finalised = false;
        // Calls the newPerson method to generate information about person
        System.out.println("Please enter the details for the customer:");
        Person customer = newPerson();
        System.out.println("Please enter the details for the architect");
        Person architect = newPerson();
        System.out.println("Please enter the details for the contractor");
        Person contractor = newPerson();
        // Sets a placeholder date for completion date as deadline
        LocalDate completionDate = deadline;
        // If the project name was left blank, generates a project name
        if (projectName == ""){
            projectName = customer.surname + " " + typeBuilding;
        }
        // Creates a new project object
        Project project = new Project(projectNum, projectName, typeBuilding, address, erfNum, cost,
                totalPaid, deadline, finalised, customer, architect, contractor, completionDate);
        // Adds new project to list
        projectList.add(project);
    }
    // Creates a new Person object for user's inputs for attributes
    public static Person newPerson (){
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
        return new Person(role, firstName, surname, phoneNum, email, address);
    }
}

/*
References

(1) Used info from here on creating an empty list:
https://stackoverflow.com/questions/8325507/adding-to-empty-list

(2) Used info from here on getting scanner to work correctly:
https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo

(3) Used info from here on converting a string to a date object:
https://www.javatpoint.com/java-string-to-date

*/