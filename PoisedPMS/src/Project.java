import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Project {
    // Attributes
    private int projectNum;
    private String projectName;
    private String typeBuilding;
    private String address;
    private String erfNum;
    private double cost;
    private double totalPaid;
    private LocalDate deadline;
    private boolean finalised;
    private Person customer;
    private Person architect;
    private Person contractor;
    private LocalDate completeDate;

    // Methods

    // Constructor
    public Project(int projectNum, String projectName, String typeBuilding, String address,
                   String erfNum, double cost, double totalPaid, LocalDate deadline,
                   boolean finalised, Person customer, Person architect, Person contractor,
                   LocalDate completeDate){
    this.setProjectNum(projectNum);
    this.setProjectName(projectName);
    this.setTypeBuilding(typeBuilding);
    this.setAddress(address);
    this.setErfNum(erfNum);
    this.setCost(cost);
    this.setTotalPaid(totalPaid);
    this.setDeadline(deadline);
    this.setFinalised(finalised);
    this.setCustomer(customer);
    this.setArchitect(architect);
    this.setContractor(contractor);
    this.setCompleteDate(completeDate);
    }

    //toString
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "Project Number: " + getProjectNum();
        output += "\nProject Name: " + getProjectName();
        output += "\nType of Building: " + getTypeBuilding();
        output += "\nAddress: " + getAddress();
        output += "\nERF Number: " + getErfNum();
        output += "\nTotal Cost: " + df.format(getCost());
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
    public static void changeDeadline(Project activeProject){
        // Asks the user what they would like to change the deadline to
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to change the deadline to? Please enter in the " +
                "format yyyy-mm-dd (i.e. 2022-07-15)");
        String newDeadlineS = input.nextLine();
        // Converts deadline into Date
        LocalDate newDeadline = LocalDate.parse(newDeadlineS);
        // Sets the new deadline to project deadline
        activeProject.setDeadline(newDeadline);
        // Prints a confirmation
        System.out.println("Deadline changed successfully.");
    }

    public static void changePaid(Project activeProject){
        // Asks the user what they would like set the total fee paid to
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to change the total amount paid to?");
        double newPaid = input.nextDouble();
        input.nextLine();
        activeProject.setTotalPaid(newPaid);
        // Prints a confirmation
        System.out.println("Amount paid changed successfully.");
    }

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

    public String getTypeBuilding() {
        return typeBuilding;
    }

    public void setTypeBuilding(String typeBuilding) {
        this.typeBuilding = typeBuilding;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getErfNum() {
        return erfNum;
    }

    public void setErfNum(String erfNum) {
        this.erfNum = erfNum;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
}
