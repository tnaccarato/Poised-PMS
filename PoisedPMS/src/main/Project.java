package main;

import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The type Project.
 */
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

    /**
     * Gets the values of each field and writes them to a string.
     *
     * @return the string with the values of each attribute.
     */
    public String getAttributes(){
        String attributes;
        attributes = "\n"+getProjectNum()+","+getProjectName()+","+building.getAttributes()+","
                +getTotalPaid()+","+getDeadline()+","+getCompleteDate()+","+isFinalised()+","
                +customer.getAttributes()+","+architect.getAttributes()+","
                +contractor.getAttributes();
        return attributes;
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectNum   the project num
     * @param projectName  the project name
     * @param building     the building
     * @param totalPaid    the total paid
     * @param deadline     the deadline
     * @param completeDate the complete date
     * @param finalised    the finalised
     * @param customer     the customer
     * @param architect    the architect
     * @param contractor   the contractor
     */
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

    /**
     * toString method.
     * @return string
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "main.Project Number: " + getProjectNum();
        output += "\nmain.Project Name: " + getProjectName();
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

    /**
     * Change deadline.
     *
     * @param activeProject the active project
     */
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

    /**
     * Change amount paid by client.
     *
     * @param activeProject the active project
     */
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
    /**
     * Gets project num.
     *
     * @return the project num
     */

    public int getProjectNum() {
        return projectNum;
    }

    /**
     * Sets project num.
     *
     * @param projectNum the project num
     */
    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets project name.
     *
     * @param projectName the project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Gets total paid.
     *
     * @return the total paid
     */
    public double getTotalPaid() {
        return totalPaid;
    }

    /**
     * Sets total paid.
     *
     * @param totalPaid the total paid
     */
    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    /**
     * Gets deadline.
     *
     * @return the deadline
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Sets deadline.
     *
     * @param deadline the deadline
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Is finalised boolean.
     *
     * @return the boolean
     */
    public boolean isFinalised() {
        return finalised;
    }

    /**
     * Sets finalised.
     *
     * @param finalised the finalised
     */
    public void setFinalised(boolean finalised) {
        this.finalised = finalised;
    }

    /**
     * Gets customer.
     *
     * @return the customer
     */
    public Person getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer
     */
    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    /**
     * Gets architect.
     *
     * @return the architect
     */
    public Person getArchitect() {
        return architect;
    }

    /**
     * Sets architect.
     *
     * @param architect the architect
     */
    public void setArchitect(Person architect) {
        this.architect = architect;
    }

    /**
     * Gets contractor.
     *
     * @return the contractor
     */
    public Person getContractor() {
        return contractor;
    }

    /**
     * Sets contractor.
     *
     * @param contractor the contractor
     */
    public void setContractor(Person contractor) {
        this.contractor = contractor;
    }

    /**
     * Gets complete date.
     *
     * @return the complete date
     */
    public LocalDate getCompleteDate() {
        return completeDate;
    }

    /**
     * Sets complete date.
     *
     * @param completeDate the complete date
     */
    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets building.
     *
     * @param building the building
     */
    public void setBuilding(Building building) {
        this.building = building;
    }
}
