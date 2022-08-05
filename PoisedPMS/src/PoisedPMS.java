import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PoisedPMS {
    public static final String DIVIDER = """

-----------------------------------------------------------------------------------------------
    """;
    // Creates an empty project list (1)
    static List<Project> projectList = new LinkedList<>();
    // Declares a count for the contact details
    static int personCount = 0;
    //Declares a count for project number
    static int projectNum = 0;

    // Calls main method
    public static void main (String [] args){
        menu();
    }

    // Displays the main menu of the application and allows for user input for selection
    public static void menu (){
        while (true){
            printMenu();
            Scanner menuInputScanner = new Scanner(System.in);
            System.out.println("Please enter your selection below:");
            String userInput = menuInputScanner.nextLine();
            // If the user enters a, allows them to add a new project
            if ("a".equals(userInput)){
                newProject();
            }
            // If the user enters v, allows them to view all projects
            else if ("v".equals(userInput)){
                viewAll();
            }
            // If the user enters cd, allows them to change the deadline of a project
            else if ("cd".equals(userInput)){
                changeDeadline();
            }
            // If the user enters cp, allows them to change the amount paid for
            else if ("cp".equals(userInput)){
                changeAmountPaid();
            }
            // If user enters uc, allows them to update contractor details
            else if ("uc".equals(userInput)){
                updateContractor();
            }
            // If user enters f, allows them to finalise all projects
            else if ("f".equals(userInput)){
                finalise();
            }
            // If user enters q, quits the program
            else if ("q".equals(userInput)) {
                System.out.println("Thank you for using the Poised Project Management System. " +
                        "Goodbye!");
                break;
            }
            // Otherwise, prints an error message and allows them to try again
            else{
                System.out.println("Input \"" + userInput + "\" not recognised, please try again.");
            }
        }
    }

    // Views all projects
    private static void viewAll() {
        // If there are no projects, print a statement saying that
        if (projectList.isEmpty()){
            System.out.println("There are currently no projects.");
        }
        // Otherwise, prints the project information
        else for (Project project: projectList){
            printProject(project);
        }
    }

    // Updates contractors information
    private static void updateContractor() {
        Scanner changeDetailsScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the contractor details " +
                "for?");
        int changeDetailsNum = changeDetailsScanner.nextInt() - 1;
        changeDetailsScanner.nextLine();
        Person.changeDetails(projectList.get(changeDetailsNum).getContractor());
    }

    // Changes the amount paid for
    private static void changeAmountPaid() {
        Scanner changePaidScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the amount paid for?");
        int changePaidNum = changePaidScanner.nextInt() - 1;
        changePaidScanner.nextLine();
        Project.changePaid(projectList.get(changePaidNum));
    }

    // Changes the deadline of a project
    private static void changeDeadline() {
        Scanner changeDeadlineScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the deadline of?");
        int changeDeadlineNum = changeDeadlineScanner.nextInt() - 1; // -1 as index starts at 0
        changeDeadlineScanner.nextLine();
        Project.changeDeadline(projectList.get(changeDeadlineNum));
    }

    // Prints a project
    private static void printProject(Project project) {
        System.out.println(DIVIDER + project + DIVIDER);
    }

    // Prints the menu options
    private static void printMenu() {
        System.out.println("Welcome to the Poised Project Management System. What would you " +
                "like to " + "do?\n");
        System.out.println("""
                a  - Add a new project
                v  - View all projects
                cd - Change the due date of the project
                cp - Change the amount the client has paid to date
                uc - Update the contact details of the contractor
                f  - Finalise a project
                q  - Quit the program
                """);
    }

    // Creates a new Project object from user's inputs for attributes and adds it to a list
    public static void newProject () {
        // Increases project number count
        projectNum++;
        // Takes user inputs for attributes of the project
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of this project? If this is left blank, " +
                "a name will be generated.");
        String projectName = input.nextLine();
        System.out.println("What type of building is being built?");
        String typeBuilding = input.nextLine();
        System.out.println("What is the address of the building?");
        String address = input.nextLine();
        System.out.println("What is the ERF number of the project?");
        String erfNum = input.nextLine();
        double cost;
        while (true) {
            System.out.println("What is the total cost of the project?");
            try {
                cost = input.nextDouble();
                input.nextLine(); // Consumes rest of line so scanner works correctly (2)
                break;
            }
            // If the user doesn't enter a number, throws an error and allows them to try again
            catch (InputMismatchException e) {
                System.out.println("You did not enter a number, please try again.");
                input.nextLine();
            }
        }
        // Creates a new Building object
        Building building = new Building(typeBuilding, address, erfNum, cost);
        double totalPaid;
        while (true) {
            System.out.println("How much has the customer already paid?");
            try {
                totalPaid = input.nextDouble();
                input.nextLine();
                break;
            }
            // If the user doesn't enter a number, throws an error and allows them to try again
            catch (InputMismatchException e) {
                System.out.println("You did not enter a number, please try again.");
                input.nextLine();
            }
        }
        LocalDate deadline;
        while (true) {
            System.out.println("When is the deadline for the project? Please enter as yyyy-mm-dd " +
                    "(i.e. 2022-07-15)");
            String deadlineS = input.nextLine();
            try {
                // Converts deadline into Date (3)
                deadline = LocalDate.parse(deadlineS);
                break;
            }
            // If the user, didn't enter a date, tries again until they enter a properly formatted
            // date
            catch (DateTimeParseException e) {
                System.out.println("Your input was not recognised, please make sure that you " +
                        "enter a date in the format provided.");
                input.nextLine();
            }
        }
        // Defaults finalised to false
        boolean finalised = false;
        // Sets a placeholder date for completion date as deadline
        LocalDate completionDate = deadline;
        // Calls the newPerson method to generate information about person
        System.out.println("Please enter the details for the customer:");
        Person customer = newPerson();
        System.out.println("Please enter the details for the architect");
        Person architect = newPerson();
        System.out.println("Please enter the details for the contractor");
        Person contractor = newPerson();

        // If the project name was left blank, generates a project name
        if (projectName.equals("")) {
            projectName = customer.getSurname() + " " + typeBuilding;
        }
        // Creates a new project object
        Project project = new Project(projectNum, projectName, building,
                totalPaid, deadline, finalised, customer, architect, contractor, completionDate);
        // Adds new project to list
        projectList.add(project);
        // Prints a confirmation that the project has been added successfully
        System.out.println("Project added to system. Returning to menu...");
    }

    // Creates a new Person object for user's inputs for attributes
    public static Person newPerson (){
        // Generates the role of the person using count
        String role = "";
        if (personCount == 0){
            role = "Customer";
            personCount ++;}
        else if (personCount == 1) {
            role = "Architect";
            personCount++;}
        else if(personCount == 2){
            role = "Contractor";
            personCount = 0;  // Resets personCount for next project
        }
        // Asks user for input and creates a new Person object
        return newPersonInput(role);
    }

    private static Person newPersonInput(String role) {
        // Takes user inputs for attributes for the person
        Scanner input = new Scanner(System.in);
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

    // Finalises all projects and generates an invoice for them
    public static void finalise(){
        // For each project in projectList:
        for (Project project: projectList){
            // Sets finalised to true
            project.setFinalised(true);
            // Sets the current date to the complete date
            project.setCompleteDate(LocalDate.now());
            // Calculates the amount the customer still has to pay
            double stillToPay = project.getBuilding().getCost() - project.getTotalPaid();
            // Prints an invoice if the amount still to pay is more than 0
            if (stillToPay > 0){
                // Declares a decimal format for cost
                DecimalFormat df = new DecimalFormat("#.##");
                System.out.println(DIVIDER);
                System.out.println("Customer still has to pay £" + df.format(stillToPay));
                System.out.println(project);
                System.out.println(DIVIDER);
            }
        }
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
