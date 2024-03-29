package com.main;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
     * The constant NOT_AN_INTEGER_ERROR for use in non-integer errors.
     */
    public static final String NOT_AN_INTEGER_ERROR = "You did not enter an integer, please try " +
            "again.";

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
    public static void main(String[] args) {
        try {
            // Connects to poisepms database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "tom",
                    "Suss3x225!"
            );
            // Creates a direct line from the database for queries
            Statement statement = connection.createStatement();
            readWriteDatabase(statement);
            menu(statement);
        }
        // Prints the stack, if an SQLException occurs
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a summary of projects for viewing when asked to choose a project
     */
    public static void projectSummary() {
        for (Project project : projectList) {
            System.out.println(project.getProjectNum() + " - " + project.getProjectName());
        }
    }

    /**
     * Reads from the poisepms database and adds each record as projects to projectList.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void readWriteDatabase(Statement statement) throws SQLException {
        // Runs an SQL query selecting each row of the table
        ResultSet projectResults = statement.executeQuery("SELECT * FROM project;");
        LocalDate completeDate;
        // Declares a second statement for use inside the method (1)
        Statement statement2 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                "tom",
                "Suss3x225!"
        ).createStatement();
        // Loops through the projectResultsSet, creating a project object from each row
        while (projectResults.next()) {
            projectNum = projectResults.getInt("PROJECT_NUM");
            String projectName = projectResults.getString("PROJECT_NAME");
            LocalDate deadline = projectResults.getDate("DEADLINE").toLocalDate();
            try {
                completeDate = projectResults.getDate("COMPLETE_DATE").toLocalDate();
            }
            // If the complete date is null, gives an error and defaults completeDate to deadline
            catch (NullPointerException e) {
                completeDate = deadline;
            }
            float totalCost = projectResults.getFloat("TOTAL_COST");
            float amountPaid = projectResults.getFloat("AMOUNT_PAID");
            boolean finalised = projectResults.getBoolean("FINALISED");
            String erfNumber = projectResults.getString("ERF_NUMBER");
            String customerID = projectResults.getString("CUSTOMER_ID");
            String architectID = projectResults.getString("ARCHITECT_ID");
            String contractorID = projectResults.getString("CONTRACTOR_ID");
            // Reads the corresponding building table record
            Building building = readBuildingTable(statement2, erfNumber);
            // Reads the corresponding customer table record
            Person customer = readCustomerTable(statement2, customerID);
            // Reads the corresponding architect table record
            Person architect = readArchitectTable(statement2, architectID);
            // Reads the corresponding contractor table record
            Person contractor = readContractorTable(statement2, contractorID);
            // Declares a new project object using the record's attributes
            Project project = new Project(projectNum, projectName, building, totalCost, amountPaid,
                    deadline, completeDate, finalised, customer, architect, contractor);
            // Adds the new object to projectList
            projectList.add(project);
        }
        // Prints a confirmation
        System.out.println("Database read successfully. " + projectList.size() +
                " projects have been added.");
    }

    /**
     * Reads the contractor table record with the matching constructorID and returns a new Person
     * object.
     *
     * @param statement2   A temporary statement for use within the readWriteDatabase method.
     * @param contractorID The ID and primary key of the contractor.
     * @return new Person object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static Person readContractorTable(Statement statement2, String contractorID)
            throws SQLException {
        ResultSet contractorResults = statement2.executeQuery("SELECT * FROM CONTRACTOR WHERE"
                + " CONTRACTOR_ID=\"" + contractorID + "\";");
        contractorResults.next();
        return new Person("Contractor",
                contractorResults.getString("CONTRACTOR_ID"),
                contractorResults.getString("CONTRACTOR_FNAME"),
                contractorResults.getString("CONTRACTOR_LNAME"),
                contractorResults.getString("CONTRACTOR_PHONE"),
                contractorResults.getString("CONTRACTOR_EMAIL"),
                contractorResults.getString("CONTRACTOR_ADDRESS"));
    }

    /**
     * Reads the architect table record with the matching architectID and returns a new Person
     * object.
     *
     * @param statement2  A temporary statement for use within the readWriteDatabase method
     * @param architectID The ID and primary key of the architect
     * @return new Person object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static Person readArchitectTable(Statement statement2, String architectID)
            throws SQLException {
        ResultSet architectResults = statement2.executeQuery("SELECT * FROM architect WHERE " +
                "ARCHITECT_ID=\"" + architectID + "\";");
        architectResults.next();
        return new Person("Architect",
                architectResults.getString("ARCHITECT_ID"),
                architectResults.getString("ARCHITECT_FNAME"),
                architectResults.getString("ARCHITECT_LNAME"),
                architectResults.getString("ARCHITECT_PHONE"),
                architectResults.getString("ARCHITECT_EMAIL"),
                architectResults.getString("ARCHITECT_ADDRESS"));
    }

    /**
     * Reads the customer table record with the matching customerID and returns a new Person object.
     *
     * @param statement2 A temporary statement for use within the readWriteDatabase method.
     * @param customerID The ID and primary key of the architect.
     * @return new Person object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static Person readCustomerTable(Statement statement2, String customerID)
            throws SQLException {
        ResultSet customerResults = statement2.executeQuery("SELECT * FROM customer WHERE " +
                "CUSTOMER_ID=\"" + customerID + "\";");
        customerResults.next();
        return new Person("Customer",
                customerResults.getString("CUSTOMER_ID"),
                customerResults.getString("CUSTOMER_FNAME"),
                customerResults.getString("CUSTOMER_LNAME"),
                customerResults.getString("CUSTOMER_PHONE"),
                customerResults.getString("CUSTOMER_EMAIL"),
                customerResults.getString("CUSTOMER_ADDRESS"));
    }

    /**
     * Reads the building table record with the matching erfNumber and returns a new Building
     * object.
     *
     * @param statement2 A temporary statement for use within the readWriteDatabase method.
     * @param erfNumber  The ID and primary key of the building.
     * @return new Building object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static Building readBuildingTable(Statement statement2, String erfNumber)
            throws SQLException {
        ResultSet buildingResults = statement2.executeQuery("SELECT * FROM building WHERE " +
                "ERF_NUMBER=\"" + erfNumber + "\";");
        buildingResults.next();
        return new Building(buildingResults.getString("BUILDING_TYPE"),
                buildingResults.getString("BUILDING_ADDRESS"),
                buildingResults.getString("ERF_NUMBER"));
    }

    /**
     * Displays the main menu of the application and allows for user input for selection.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void menu(Statement statement) throws SQLException {
        while (true) {
            printMenu();
            Scanner menuInputScanner = new Scanner(System.in);
            System.out.println("Please enter your selection below:");
            String userInput = menuInputScanner.nextLine();
            // If the user enters a, allows them to add a new project
            if ("a".equals(userInput)) {
                newProject(statement);
            } else if ("s".equals(userInput)) {
                searchProject(statement);
            }
            // If the user enters v, allows them to view all projects
            else if ("va".equals(userInput)) {
                viewAll(statement);
            } else if ("vi".equals(userInput)) {
                incompleteProjects(statement);
            } else if ("vo".equals(userInput)) {
                overdueProjects(statement);
            }
            // If the user enters cd, allows them to change the deadline of a project
            else if ("cd".equals(userInput)) {
                changeDeadline(statement);
            }
            // If the user enters cp, allows them to change the amount paid for
            else if ("cp".equals(userInput)) {
                changeAmountPaid(statement);
            }
            // If user enters uc, allows them to update contact details
            else if ("uc".equals(userInput)) {
                updateContact(statement);
            }
            // If user enters f, allows them to finalise a project
            else if ("f".equals(userInput)) {
                finalise(statement);
            }
            // If user enters q,  quits the program
            else if ("q".equals(userInput)) {
                System.out.println("Thank you for using the Poised Project Management System. " +
                        "Goodbye!");
                System.exit(0);
                break;
            }
            // Otherwise, prints an error message and allows them to try again
            else {
                System.out.println("Input \"" + userInput + "\" not recognised, please try again.");
            }
        }
    }

    /**
     * Allows user to view incomplete projects and prints them.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void incompleteProjects(Statement statement) throws SQLException {
        noProjects(statement);
        // Searches for incomplete projects and prints them
        boolean incompleteProjectsFound = false;
        for (Project project : projectList) {
            if (!project.isFinalised()) {
                System.out.println(DIVIDER);
                System.out.println(project + "\n");
                System.out.println(DIVIDER);
                incompleteProjectsFound = true;
            }
        }
        // If no incomplete projects were found, prints a statement
        if (!incompleteProjectsFound) {
            System.out.println("No incomplete projects were found. Great work!");
        }
    }

    /**
     * Allows user to search for overdue projects and prints them.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void overdueProjects(Statement statement) throws SQLException {
        noProjects(statement);
        boolean overdueProjectsFound = false;
        // Searches for overdue projects and prints them
        for (Project project : projectList) {
            if (project.getDeadline().isBefore(LocalDate.now()) && (!project.isFinalised())) {
                System.out.println(DIVIDER);
                System.out.println(project + "\n");
                System.out.println(DIVIDER);
                overdueProjectsFound = true;
            }
        }
        // If no overdue projects were found, prints a statement
        if (!overdueProjectsFound) {
            System.out.println("No overdue projects were found. Keep it up!");
        }
    }

    /**
     * Allows user to view the details of a project based on either the project number or the
     * project name.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void searchProject(Statement statement) throws SQLException {
        noProjects(statement);
        // Asks the user how they would like to search for a project
        Scanner searchProjectScanner = new Scanner(System.in);
        int searchProjectMethod;
        searchProjectMethod = chooseSearchMethod(searchProjectScanner);
        if (searchProjectMethod == 1) {
            searchByNum(searchProjectScanner);
        } else if (searchProjectMethod == 2) {
            searchProjectName(searchProjectScanner);
        }
    }

    /**
     * Allows user to search for a project by name
     *
     * @param searchProjectScanner input scanner
     */
    private static void searchProjectName(Scanner searchProjectScanner) {
        // Asks the user for the name of the project they want to search for
        System.out.println("What is the name of the project you would like to search?");
        String searchProjectName = searchProjectScanner.nextLine();
        boolean projectFound = false;
        for (Project project : projectList) {
            // If a project can be found with that name, prints it
            if (project.getProjectName().equals(searchProjectName)) {
                System.out.println(project + "\n");
                projectFound = true;
            }
        }
        // If it can't, prints an error message and allows the user to try again
        if (!projectFound) {
            System.out.println("A project with that name could not be found. Please try again.");
            searchProjectName(searchProjectScanner);
        }
    }

    /**
     * Allows user to search by project number
     *
     * @param searchProjectScanner input scanner
     */
    private static void searchByNum(Scanner searchProjectScanner) {
        while (true) {
            // Asks user for the project number they want to search for
            System.out.println("Which project number would you like to select?");
            try {
                int searchProjectNum = searchProjectScanner.nextInt() - 1;
                searchProjectScanner.nextLine();
                System.out.println(projectList.get(searchProjectNum) + "\n");
                break;
            }
            // If the user doesn't enter an integer, allows them to try again
            catch (InputMismatchException e) {
                System.out.println(NOT_AN_INTEGER_ERROR);
                searchProjectScanner.nextLine();
            }
            // If the project index is out of bounds, allows the user to try again
            catch (IndexOutOfBoundsException e) {
                System.out.println("A project in that index could not be found, please try again.");
            }
        }
    }

    /**
     * Allows chooses the input method for search method.
     *
     * @param searchProjectScanner input scanner
     * @return int user choice
     */
    private static int chooseSearchMethod(Scanner searchProjectScanner) {
        int searchProjectMethod;
        while (true) {
            System.out.println("""
                    1 - By Project Number
                    2 - By Project Name""");
            try {
                searchProjectMethod = searchProjectScanner.nextInt();
                if (searchProjectMethod > 2 || searchProjectMethod <= 0) {
                    throw new IllegalArgumentException();
                }
                searchProjectScanner.nextLine();
                break;
            }
            // If user didn't enter an integer, allows them to try again
            catch (InputMismatchException e) {
                System.out.println(NOT_AN_INTEGER_ERROR);
                searchProjectScanner.nextLine();
            }
            // If the user enters an integer other than 1 or 2, allows them to try again
            catch (IllegalArgumentException e) {
                System.out.println("Input not recognised, please try again.");
                searchProjectScanner.nextLine();
            }
        }
        return searchProjectMethod;
    }


    /**
     * Views all the projects and prints them in an easy-to-read format.
     */
    private static void viewAll(Statement statement) throws SQLException {
        // If there are no projects, print a statement saying that
        noProjects(statement);
        // Otherwise, prints the project information
        for (Project project : projectList) {
            printProject(project);
        }
    }

    /**
     * Updates contact details for a selected Person object.
     *
     * @param statement the statement
     * @throws SQLException if there is a problem with the SQL query.
     */
    private static void updateContact(Statement statement) throws SQLException {
        noProjects(statement);
        Scanner changeDetailsScanner = new Scanner(System.in);
        // Asks the user which project they would like to change details for
        System.out.println("Which project would you like to change the contact details " +
                "for?");
        projectSummary();
        int changeDetailsNum;
        changeDetailsNum = selectChangeDetailsNum(changeDetailsScanner);

        // Asks the user which person they would like to change the details for
        while (true) {
            System.out.println("Which person would you like to change the details for?");
            System.out.println("""
                    1 - Customer
                    2 - Architect
                    3 - Contractor""");
            int changeDetailsPerson = changeDetailsScanner.nextInt();

            // Calls Person changeDetails method on the selected Person object
            boolean inputRecognised = false;
            // Customer
            if (changeDetailsPerson == 1) {
                Person.changeDetails(projectList.get(changeDetailsNum).getCustomer(), statement);
                changeDetailsScanner.nextLine();
                // Changes the project name to match the new name of the customer
                String newProjectName = projectList.get(changeDetailsNum)
                        .getCustomer().getSurname()
                        + " " + projectList.get(changeDetailsNum).getBuilding().getTypeBuilding();
                projectList.get(changeDetailsNum).setProjectName(newProjectName);
                inputRecognised = true;
                // Changes the corresponding database entry
                statement.executeUpdate("UPDATE project SET PROJECT_NAME=\""
                        + newProjectName + "\" WHERE  PROJECT_NUM=" + changeDetailsNum + ";");
            }
            // Architect
            else if (changeDetailsPerson == 2) {
                Person.changeDetails(projectList.get(changeDetailsNum).getArchitect(), statement);
                changeDetailsScanner.nextLine();
                inputRecognised = true;

            }
            // Contractor
            else if (changeDetailsPerson == 3) {
                Person.changeDetails(projectList.get(changeDetailsNum).getContractor(), statement);
                changeDetailsScanner.nextLine();
                inputRecognised = true;
            }
            // Otherwise, prints an error and allows them to try again.
            else {
                System.out.println("Input was not recognised, please try again.");
                changeDetailsScanner.nextLine();
            }
            // If the input is recognised, breaks the loop
            if (inputRecognised) {
                break;
            }
        }
    }

    /**
     * Allows user to pick a project number for use in changeDetails method.
     *
     * @param changeDetailsScanner input scanner
     * @return int project number
     */
    private static int selectChangeDetailsNum(Scanner changeDetailsScanner) {
        int changeDetailsNum;
        while (true) {
            try {
                changeDetailsNum = changeDetailsScanner.nextInt() - 1;
                if (changeDetailsNum > projectList.size()) {
                    throw new IllegalArgumentException();
                } else if (changeDetailsNum < 0) {
                    throw new IndexOutOfBoundsException();
                }
                changeDetailsScanner.nextLine();
                break;
            }
            // If the user didn't enter an integer, throws an error and allows the user to try again
            catch (InputMismatchException e) {
                System.out.println(NOT_AN_INTEGER_ERROR);
                changeDetailsScanner.nextLine();
            }
            // If the project index will be out of range, throws an error and allows the user to try
            // again
            catch (IllegalArgumentException e) {
                System.out.println("Project index was out of range, please try again.");
                changeDetailsScanner.nextLine();
            }
            // If the user enters 0, throws an error and allows the user to try again
            catch (IndexOutOfBoundsException e) {
                System.out.println("Project number must be greater than 0. Please try again.");
                changeDetailsScanner.nextLine();
            }
        }
        return changeDetailsNum;
    }

    /**
     * Changes the amount paid for by the client.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query
     */
    private static void changeAmountPaid(Statement statement) throws SQLException {
        noProjects(statement);
        Scanner changePaidScanner = new Scanner(System.in);
        System.out.println("Which project would you like to change the amount paid for?");
        projectSummary();
        int changePaidNum = changePaidScanner.nextInt() - 1;
        changePaidScanner.nextLine();
        Project.changePaid(projectList.get(changePaidNum), statement);
    }

    /**
     * Changes the deadline of a project.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static void changeDeadline(Statement statement) throws SQLException {
        noProjects(statement);
        Scanner changeDeadlineScanner = new Scanner(System.in);
        int changeDeadlineNum;
        // Asks the user which project they want to change the deadline for
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
                Project.changeDeadline(projectList.get(changeDeadlineNum), statement);
                break;
            }
            // If the index is not recognised gives an error and allows them to try again or
            // return to menu
            catch (IndexOutOfBoundsException outOfBounds) {
                System.out.println("The project with the index you entered (" +
                        (changeDeadlineNum) + ") " + "was not found. Would you like to try " +
                        "again? (y/n)");
                invalidIndex(changeDeadlineScanner, statement);
            }
        }
    }

    /**
     * Provides a menu for retrying changeDeadline after invalid index
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static void invalidIndex(Scanner changeDeadlineScanner, Statement statement)
            throws SQLException {
        try {
            var userResponse = changeDeadlineScanner.nextLine();
            if (userResponse.equals("y")) {
                changeDeadline(statement);
            } else if (userResponse.equals("n")) {
                System.out.println("Returning to menu...");
                menu(statement);
            } else throw new InputMismatchException();
        }
        // If the user didn't enter y or n, allows them to try again
        catch (InputMismatchException yesOrNo) {
            System.out.println("Input not recognised please enter either y or n:");
            invalidIndex(changeDeadlineScanner, statement);
        }
    }

    /**
     * Prints a project.
     *
     * @param project The selected project object.
     */
    private static void printProject(Project project) {
        System.out.println(DIVIDER + project
                + DIVIDER);
    }

    /**
     * Prints the menu options
     */
    private static void printMenu() {
        System.out.println("Welcome to the Poised Project Management System. What would you " +
                "like to " + "do?\n");
        System.out.println("""
                a  - Add a new project
                s  - Search for a particular project
                va - View all projects
                vi - View incomplete projects
                vo - View overdue projects
                cd - Change the due date of the project
                cp - Change the amount the client has paid to date
                uc - Update the contact details
                f  - Finalise a project
                q  - Quit the program
                """);
    }

    /**
     * Creates a new Project object from user's inputs for attributes and adds it to a list.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void newProject(Statement statement) throws SQLException {
        // Increases project number count by one
        ResultSet results = statement.executeQuery("SELECT MAX(PROJECT_NUM) FROM project;");
        results.next();
        projectNum = results.getInt(1) + 1;
        // Takes user inputs for attributes of the project
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of this project? If this is left blank, " +
                "a name will be generated.");
        String projectName = input.nextLine();
        System.out.println("What type of building is being built?");
        String typeBuilding = input.nextLine();
        System.out.println("What is the address of the building?");
        String address = input.nextLine();
        String erfNum;
        erfNum = inputERF(input);
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
        Building building = new Building(typeBuilding, address, erfNum);
        // Adds building to database
        statement.executeUpdate("INSERT INTO building VALUES( \""
                + erfNum + "\", \"" + address + "\", \"" + typeBuilding + "\");");
        double amountPaid;
        while (true) {
            System.out.println("How much has the customer already paid?");
            try {
                amountPaid = input.nextDouble();
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
        Person customer = newPerson(statement);
        System.out.println("Please enter the details for the architect");
        Person architect = newPerson(statement);
        System.out.println("Please enter the details for the contractor");
        Person contractor = newPerson(statement);

        // If the project name was left blank, generates a project name
        if (projectName.equals("")) {
            projectName = customer.getSurname() + " " + typeBuilding;
        }
        // Creates a new project object
        Project project = new Project(projectNum, projectName, building, cost,
                amountPaid, deadline, completionDate, finalised, customer, architect, contractor);
        // Adds new project to list
        projectList.add(project);
        // Adds the project to database
        statement.executeUpdate("INSERT INTO project VALUES("
                + projectNum + ",\"" + projectName + "\",\"" + deadline + "\", NULL," + cost + ","
                + amountPaid + ", FALSE" + ",\"" + erfNum + "\",\"" + architect.getId() + "\",\""
                + customer.getId() + "\",\"" + contractor.getId() + "\");");
        // Prints a confirmation that the project has been added successfully
        System.out.println("Project added to system. Returning to menu...");
    }

    /**
     * Allows the user to input the ERF number
     *
     * @param input the ERF number
     * @return the inputted ERF number
     */
    private static String inputERF(Scanner input) {
        String erfNum;
        while (true) {
            try {
                System.out.println("What is the ERF number of the project?");
                erfNum = input.nextLine();
                // Checks that ERF isn't already in use
                for (Project project : projectList) {
                    if (project.getBuilding().getErfNum().equals(erfNum)) {
                        throw new SQLIntegrityConstraintViolationException();
                    }
                }
                if (erfNum.length() > 10) {
                    throw new TooManyCharactersException();
                } else {
                    break;
                }
            } catch (TooManyCharactersException e) {
                System.out.println("You have entered too many characters, ERF number should " +
                        "only be up to 10 characters. Please try again.");
                input.nextLine();
            }
            // If the ERF already exists, throws an error and allows the user to try again
            catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("The ERF number you entered is in use. Please try again.");
            }
        }
        return erfNum;
    }

    /**
     * Creates a new Person object for user's inputs for attributes.
     *
     * @param statement the statement
     * @return New Person object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static Person newPerson(Statement statement) throws SQLException {
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
        // Asks user for input and creates a new Person object
        return newPersonInput(role, statement);
    }

    /**
     * Takes user inputs for attributes for the person.
     *
     * @param statement the statement
     * @param role      The role of the Person object.
     * @return new Person object.
     * @throws SQLException if there is an issue with the SQL query.
     */
    private static Person newPersonInput(String role, Statement statement) throws SQLException {
        Scanner input = new Scanner(System.in);
        String id;
        while (true) {
            try {
                System.out.println("What is this person's ID number?");
                id = input.nextLine();
                // Checks that user hasn't entered more than 5 digits
                if (id.length() > 5) {
                    throw new TooManyCharactersException();
                }
                // Checks that ID is not a duplicate by trying to create a record with it
                statement.executeUpdate("INSERT INTO " + role.toLowerCase()
                        + " VALUES(\"" + id + "\",\"IS\",\"THIS\",\"ID\",\"A\"," +
                        "\"DUPLICATE?\");");
                // Deletes the test record
                statement.executeUpdate("DELETE FROM " + role.toLowerCase() + " WHERE "
                        + role.toUpperCase() + "_ID=\"" + id + "\";");
                break;
            } catch (TooManyCharactersException e) {
                System.out.println("You have entered too many characters, ID should only " +
                        "be up to 5 characters. Please try again.");
            }
            // If the ID is a duplicate, asks them to enter something else
            catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("This ID is already in use, please try again.");
            }
        }
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
        // Adds the person to databases
        statement.executeUpdate("INSERT INTO " + role.toLowerCase() + " VALUES(\""
                + id + "\",\"" + firstName + "\",\"" + surname + "\",\"" + phoneNum
                + "\",\"" + email + "\",\"" + address + "\");");
        return new Person(role, id, firstName, surname, phoneNum, email, address);
    }

    /**
     * Prints a statement if there are currently no projects to edit and returns to menu.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void noProjects(Statement statement) throws SQLException {
        if (projectList.isEmpty()) {
            System.out.println("There are currently no projects.");
            menu(statement);
        }
    }

    /**
     * Asks user to select a project they want to finalise and generates an invoice for them.
     *
     * @param statement the statement
     * @throws SQLException if there is an issue with the SQL query.
     */
    public static void finalise(Statement statement) throws SQLException {
        noProjects(statement);
        // Asks user which project they want to finalise
        Scanner finaliseInput = new Scanner(System.in);
        int finaliseChoice;
        while (true) {
            try {
                System.out.println("Which project would you like to finalise?");
                projectSummary();
                finaliseChoice = finaliseInput.nextInt() - 1;
                if (finaliseChoice > projectList.size()) {
                    throw new IllegalArgumentException();
                } else if (finaliseChoice < 0) {
                    throw new IndexOutOfBoundsException();
                }
                finaliseInput.nextLine();
                break;
            }
            // If the user did not enter an integer, throws an error and allows them to try again
            catch (InputMismatchException e) {
                System.out.println(NOT_AN_INTEGER_ERROR);
                finaliseInput.nextLine();
            }
            // If the project index will be out of range, throws an error and allows the user to try
            // again
            catch (IllegalArgumentException e) {
                System.out.println("Project index was out of range, please try again.");
                finaliseInput.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Project number must be greater than 0. Please try again.");
            }
        }

        // Changes project details for the selected project
        // Sets finalised to true
        projectList.get(finaliseChoice).setFinalised(true);
        // Sets the current date to the complete date
        projectList.get(finaliseChoice).setCompleteDate(LocalDate.now());
        // Updates the database
        statement.executeUpdate("UPDATE project SET FINALISED=TRUE, COMPLETE_DATE=\""
                + LocalDate.now() + "\" WHERE PROJECT_NUM=" + (finaliseChoice + 1) + ";");
        // Calculates the amount the customer still has to pay
        double stillToPay = projectList.get(finaliseChoice).getCost()
                - projectList.get(finaliseChoice).getTotalPaid();
        // Prints an invoice if the amount still to pay is more than 0
        if (stillToPay > 0) {
            // Declares a decimal format for cost
            DecimalFormat df = new DecimalFormat("#,##0.00");
            System.out.println(DIVIDER);
            System.out.println("Customer still has to pay £" + df.format(stillToPay));
            System.out.println(projectList.get(finaliseChoice));
            System.out.println(DIVIDER);
        }
    }
}

/*
References

(1) Used info from here on using multiple statements to prevent errors from the ResultSet being
closed when the loop starts again.
https://www.ibm.com/mysupport/s/question/0D50z00005q84FBCAY/invalid-operation-result-set-is-closed?language=en_US

(2) Used info from here on getting scanner to work correctly:
https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo

(3) Used info from here on converting a string to a date object:
https://www.javatpoint.com/java-string-to-date

*/
