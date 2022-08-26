package com.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * The main class of the program.
 */
public class PoisedPMS {
    /**
     * The constant DIVIDER.
     */
    public static final String DIVIDER = """

            -----------------------------------------------------------------------------------------------
                """;
    /**
     * The constant PROJECTS_TXT for storing the projects.txt file path.
     */
    public static final String PROJECTS_TXT = "src\\com\\main\\projects.txt";
    /**
     * The constant FIELDHEADERS for storing the headings for each field of the project.
     */
    public static final String FIELDHEADERS = "projectNum,projectName," +
            "buildingType,buildingAddress,buildingERF,buildingTotalCost," +
            "totalPaid,deadline,completeDate,finalised,customerRole, " +
            "customerFirstName,customerSurname,customerTelephone," +
            "customerEmail,customerAddress,architectRole,architectFirstName," +
            "architectLastName,architectTel,architectEmail,architectAddress," +
            "contractorRole,contractorFirstName,contractorLastName," +
            "contractorTel,contractorEmail,contractorAddress";
    /**
     * The constant projectList for storing the project objects.
     */
    static List<Project> projectList = new LinkedList<>();
    /**
     * The constant personCount for use in determining the role of Person object.
     */
    static int personCount = 0;
    /**
     * The constant projectNum for increasing the projectNum after each project is added.
     */
    static int projectNum = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
// Calls main method
    public static void main(String[] args) {
        // Creates a new projects.txt text file
        readWriteFile();
        menu();
    }

    /**
     * Generates a summary of projects for viewing when asked to choose a project
     */
    public static void projectSummary(){
        for(Project project:projectList){
            System.out.println(project.getProjectNum() + " - " + project.getProjectName());
        }
    }

    /**
     * Reads from the projects.txt file and if one does not exist, creates it.
     */
    private static void readWriteFile() {
        try {
            File projectsFile = new File(PROJECTS_TXT);
            // If a new file is created
            if (projectsFile.createNewFile()) {
                // Appends a line with the fields of the project and prints a confirmation
                Files.write(Paths.get(PROJECTS_TXT), FIELDHEADERS.getBytes(),
                        StandardOpenOption.APPEND);
                System.out.println("New projects.txt file has been created in" + PROJECTS_TXT +
                        "directory.");
            }

            // If file already exists, adds each line as a project to projectList
            else {
                System.out.println("projects.txt file already exists. Reading from file.");
                Scanner fileReader = new Scanner(projectsFile);
                fileReader.nextLine();  // Skips the first line (fields)
                while (fileReader.hasNext()) {
                    String line = fileReader.nextLine();
                    // Splits each line into a list of parameters and adds to a new ArrayList
                    String[] projectParameters = line.split(",");
                    ArrayList<String> projectParametersList = new ArrayList<>();
                    Collections.addAll(projectParametersList, projectParameters);
                    // Creates a project using the parameters given
                    Building building = new Building(projectParametersList.get(2),
                            projectParametersList.get(3), projectParametersList.get(4),
                            Double.parseDouble(projectParametersList.get(5)));
                    Person customer = new Person(projectParametersList.get(10),
                            projectParametersList.get(11), projectParametersList.get(12),
                            projectParametersList.get(13), projectParametersList.get(14),
                            projectParametersList.get(15));
                    Person architect = new Person(projectParametersList.get(16),
                            projectParametersList.get(17), projectParametersList.get(18),
                            projectParametersList.get(19), projectParametersList.get(20),
                            projectParametersList.get(21));
                    Person contractor = new Person(projectParametersList.get(22),
                            projectParametersList.get(23), projectParametersList.get(24),
                            projectParametersList.get(25), projectParametersList.get(26),
                            projectParametersList.get(27));
                    Project project = new Project(parseInt(projectParametersList.get(0)),
                            projectParametersList.get(1), building,
                            Double.parseDouble(projectParametersList.get(6)),
                            LocalDate.parse(projectParametersList.get(7)),
                            LocalDate.parse(projectParametersList.get(8)),
                            Boolean.parseBoolean(projectParametersList.get(9)),
                            customer, architect, contractor);
                    // Adds project to projectList
                    projectList.add(project);
                }
                // Closes the scanner
                fileReader.close();
            }
        }
        // If an error occurs, prints the stack
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the main menu of the application and allows for user input for selection.
     */
    public static void menu() {
        while (true) {
            printMenu();
            Scanner menuInputScanner = new Scanner(System.in);
            System.out.println("Please enter your selection below:");
            String userInput = menuInputScanner.nextLine();
            // If the user enters a, allows them to add a new project
            if ("a".equals(userInput)) {
                newProject();
            }
            // If the user enters v, allows them to view all projects
            else if ("v".equals(userInput)) {
                viewAll();
            }
            // If the user enters cd, allows them to change the deadline of a project
            else if ("cd".equals(userInput)) {
                changeDeadline();
            }
            // If the user enters cp, allows them to change the amount paid for
            else if ("cp".equals(userInput)) {
                changeAmountPaid();
            }
            // If user enters uc, allows them to update contractor details
            else if ("uc".equals(userInput)) {
                updateContractor();
            }
            // If user enters f, allows them to finalise all projects
            else if ("f".equals(userInput)) {
                finalise();
            }
            // If user enters q, quits the program
            else if ("q".equals(userInput)) {
                // Rewrites the project txt file
                updateTextFile();
                System.out.println("Thank you for using the Poised Project Management System. " +
                        "Goodbye!");
                break;
            }
            // Otherwise, prints an error message and allows them to try again
            else {
                System.out.println("Input \"" + userInput + "\" not recognised, please try again.");
            }
        }
    }

    /**
     * Updates the projects.txt file using the projectsList data.
     */
    private static void updateTextFile() {
        try {
            Path getTextPath = Paths.get(PROJECTS_TXT);
            Files.write(getTextPath, FIELDHEADERS.getBytes());
            for (Project project : projectList) {
                Files.write(getTextPath,
                        project.getAttributes().getBytes(),
                        StandardOpenOption.APPEND);
            }
        }
        // If an error occurs, prints the stack
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Views all the projects and prints them in an easy-to-read format.
     */
    private static void viewAll() {
        // If there are no projects, print a statement saying that
        if (projectList.isEmpty()) {
            System.out.println("There are currently no projects.");
        }
        // Otherwise, prints the project information
        else for (Project project : projectList) {
            printProject(project);
        }
    }

    // Updates contractors information
    private static void updateContractor() {
        Scanner changeDetailsScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the contractor details " +
                "for?");
        projectSummary();
        int changeDetailsNum = changeDetailsScanner.nextInt() - 1;
        changeDetailsScanner.nextLine();
        Person.changeDetails(projectList.get(changeDetailsNum).getContractor());
    }

    /**
     * Changes the amount paid for by the client.
     */
    private static void changeAmountPaid() {
        Scanner changePaidScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the amount paid for?");
        projectSummary();
        int changePaidNum = changePaidScanner.nextInt() - 1;
        changePaidScanner.nextLine();
        Project.changePaid(projectList.get(changePaidNum));
    }

    /**
     * Changes the deadline of a project.
     */
    private static void changeDeadline() {
        Scanner changeDeadlineScanner = new Scanner(System.in);
        int changeDeadlineNum;
        while (true) {
            System.out.println("Which project would you like to change the deadline of?");
            projectSummary();
            try {
                changeDeadlineNum = changeDeadlineScanner.nextInt() - 1;
                changeDeadlineScanner.nextLine();
                break;
            }
            // If the user didn't enter a number, throw error and allow them to try again
            catch (InputMismatchException notANum) {
                System.out.println("You did not enter a number please try again.");
                changeDeadlineScanner.nextLine();
            }
        }
        while (true) {
            try {
                Project.changeDeadline(projectList.get(changeDeadlineNum));
                break;
            }
            // If the index is not recognised gives an error and allows them to try again or
            // return to menu
            catch (IndexOutOfBoundsException outOfBounds) {
                System.out.println("The project with the index you entered (" +
                        (changeDeadlineNum) + ") " + "was not found. Would you like to try " +
                        "again? (y/n)");
                invalidIndex(changeDeadlineScanner);
            }
        }
    }

    /**
     * Provides a menu for retrying changeDeadline after invalid index
     */
    private static void invalidIndex(Scanner changeDeadlineScanner) {
        try {
            var userResponse = changeDeadlineScanner.nextLine();
            if (userResponse.equals("y")) {
                changeDeadline();
            } else if (userResponse.equals("n")) {
                System.out.println("Returning to menu...");
                menu();
            } else throw new InputMismatchException();
        }
        // If the user didn't enter y or n, allows them to try again
        catch (InputMismatchException yesOrNo) {
            System.out.println("Input not recognised please enter either y or n:");
            invalidIndex(changeDeadlineScanner);
        }
    }

    /**
     * Prints a project.
     *
     * @param project The selected project object.
     */
    private static void printProject(Project project) {
        System.out.println(DIVIDER + project + DIVIDER);
    }

    /**
     * Prints the menu options
     */
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

    /**
     * Creates a new Project object from user's inputs for attributes and adds it to a list.
     */
    public static void newProject() {
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
        // Creates a new com.main.Building object
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
                System.out.println("Press Enter to try again:");
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
                totalPaid, deadline, completionDate, finalised, customer, architect, contractor);
        // Adds new project to list
        projectList.add(project);
        // Adds the project to text file
        try {
            Files.write(Paths.get(PROJECTS_TXT), project.getAttributes().getBytes(),
                    StandardOpenOption.APPEND);
        }
        // If an error occurs, prints the stack
        catch (IOException e) {
            e.printStackTrace();
        }

        // Prints a confirmation that the project has been added successfully
        System.out.println("com.main.Project added to system. Returning to menu...");
    }

    /**
     * Creates a new com.main.Person object for user's inputs for attributes.
     *
     * @return New Person object.
     */
    public static Person newPerson() {
        // Generates the role of the person using count
        String role = "";
        if (personCount == 0) {
            role = "Customer";
            personCount++;
        } else if (personCount == 1) {
            role = "Architect";
            personCount++;
        } else if (personCount == 2) {
            role = "Contractor";
            personCount = 0;  // Resets personCount for next project
        }
        // Asks user for input and creates a new com.main.Person object
        return newPersonInput(role);
    }

    /**
     * Takes user inputs for attributes for the person.
     *
     * @param role The role of the Person object.
     * @return new Person object.
     */
    private static Person newPersonInput(String role) {
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

    /**
     * Asks user to select a project they want to finalise and generates an invoice for them.
     */
    public static void finalise() {
        // Asks user which project they want to finalise
        System.out.println("Which project would you like to finalise?");
        projectSummary();
        Scanner finaliseInput = new Scanner(System.in);
        int finaliseChoice;
        while (true) {
            try {
                finaliseChoice = finaliseInput.nextInt() - 1;
                if(finaliseChoice > projectList.size()){
                    throw new IllegalArgumentException();
                }
                finaliseInput.nextLine();
                finaliseInput.close();
                break;
            }
            // If the user did not enter an integer, throws an error and allows them to try again
            catch (InputMismatchException e) {
                System.out.println("You did not enter an integer, please try again.");
                finaliseInput.nextLine();
            }
            // If the project index will be out of range, throws an error and allows the user to try
            // again
            catch(IllegalArgumentException e){
                System.out.println("Project index was out of range, please try again.");
                finaliseInput.nextLine();
            }
        }

        // Changes project details for the selected project
        // Sets finalised to true
        projectList.get(finaliseChoice).setFinalised(true);
        // Sets the current date to the complete date
        projectList.get(finaliseChoice).setCompleteDate(LocalDate.now());
        // Calculates the amount the customer still has to pay
        double stillToPay = projectList.get(finaliseChoice).getBuilding().getCost()
                - projectList.get(finaliseChoice).getTotalPaid();
        // Prints an invoice if the amount still to pay is more than 0
        if (stillToPay > 0) {
            // Declares a decimal format for cost
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(DIVIDER);
            System.out.println("Customer still has to pay £" + df.format(stillToPay));
            System.out.println(projectList.get(finaliseChoice));
            System.out.println(DIVIDER);
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
