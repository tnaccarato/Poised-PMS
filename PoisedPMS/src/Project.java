import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project {
    // Attributes
    private int projectNum;
    private String projectName;
    private Building building;
    private double totalPaid;
    private LocalDate deadline;
    private boolean finalised;
    private Person customer;
    private Person architect;
    private Person contractor;
    private LocalDate completeDate;

    // Methods

    // Constructor
    public Project(int projectNum, String projectName, Building building, double totalPaid,
                   LocalDate deadline, LocalDate completeDate, boolean finalised, Person customer,
                   Person architect, Person contractor){
    this.setProjectNum(projectNum);
    this.setProjectName(projectName);
    this.setBuilding(building);
    this.setTotalPaid(totalPaid);
    this.setDeadline(deadline);
    this.setCompleteDate(completeDate);
    this.setFinalised(finalised);
    this.setCustomer(customer);
    this.setArchitect(architect);
    this.setContractor(contractor);
    }

    // Gets the values of each field and writes them to a string
    public String getAttributes(){
        String attributes;
        attributes = "\n"+getProjectNum()+","+getProjectName()+","+building.getAttributes()+","
                +getTotalPaid()+","+getDeadline()+","+getCompleteDate()+","+isFinalised()+","
                +customer.getAttributes()+","+architect.getAttributes()+","
                +contractor.getAttributes();
        return attributes;
    }

    //toString
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "Project Number: " + getProjectNum();
        output += "\nProject Name: " + getProjectName();
        output += getBuilding();
        output += "\nAmount Paid: " + df.format(getTotalPaid());
        output += "\nDeadline: " + getDeadline();
        // If the project is finalised, adds the completion date
        if (isFinalised()){
            output += "\nFinalised?: Yes";
            output += "\nCompletion Date: " + getCompleteDate();}
        else{
            output += "\nFinalised?: No";}
        output += "\n\nCustomer Details:\n" + getCustomer();
        output += "\n\nArchitect Details:\n" + getArchitect();
        output += "\n\nContractor Details:\n" + getContractor();
        return output;
    }

    // Change deadline
    public static void changeDeadline(Project activeProject) {
        // Asks the user what they would like to change the deadline to
        Scanner input = new Scanner(System.in);
        LocalDate newDeadline;
        while (true) {
            System.out.println("What would you like to change the deadline to? Please enter in the " +
                    "format yyyy-mm-dd (i.e. 2022-07-15)");
            String newDeadlineS = input.nextLine();
            try {
                // Converts deadline into Date
                newDeadline = LocalDate.parse(newDeadlineS);
                break;
            }
            catch (DateTimeException e) {
                System.out.println("Your input was not recognised, please make sure that you" +
                        " enter a date in the format provided.");
                System.out.println("Press Enter to try again:");
                input.nextLine();
            }
        }
        // Sets the new deadline to project deadline
        activeProject.setDeadline(newDeadline);
        // Prints a confirmation
        System.out.println("Deadline changed successfully.");
    }

    public static void changePaid(Project activeProject) {
        // Asks the user what they would like set the total fee paid to
        Scanner input = new Scanner(System.in);
        double newPaid;
        while (true) {
            try {
                System.out.println("What would you like to change the total amount paid to?");
                newPaid = input.nextDouble();
                input.nextLine();
                break;
            }
            // If the user doesn't enter a number, gives an error and allows them to try again
            catch (InputMismatchException e) {
                System.out.println("Your input was not recognised. Please enter a number and " +
                        "try again");
                input.nextLine();
            }
        }
        activeProject.setTotalPaid(newPaid);
        // Prints a confirmation
        System.out.println("Amount paid changed successfully.");
    }

    // Getters and Setters
    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isFinalised() {
        return finalised;
    }

    public void setFinalised(boolean finalised) {
        this.finalised = finalised;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Person getArchitect() {
        return architect;
    }

    public void setArchitect(Person architect) {
        this.architect = architect;
    }

    public Person getContractor() {
        return contractor;
    }

    public void setContractor(Person contractor) {
        this.contractor = contractor;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
