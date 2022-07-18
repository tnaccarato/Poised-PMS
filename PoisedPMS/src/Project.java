import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Project {
    // Attributes
    int projectNum;
    String projectName;
    String typeBuilding;
    String address;
    String erfNum;
    double cost;
    double totalPaid;
    LocalDate deadline;
    boolean finalised;
    Person customer;
    Person architect;
    Person contractor;
    LocalDate completeDate;

    // Methods

    // Constructor
    public Project(int projectNum, String projectName, String typeBuilding, String address,
                   String erfNum, double cost, double totalPaid, LocalDate deadline,
                   boolean finalised, Person customer, Person architect, Person contractor,
                   LocalDate completeDate){
    this.projectNum = projectNum;
    this.projectName = projectName;
    this.typeBuilding = typeBuilding;
    this.address = address;
    this.erfNum = erfNum;
    this.cost = cost;
    this.totalPaid = totalPaid;
    this.deadline = deadline;
    this.finalised = finalised;
    this.customer = customer;
    this.architect = architect;
    this.contractor = contractor;
    this.completeDate = completeDate;
    }

    //toString
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "Project Number: " + projectNum;
        output += "\nProject Name: " + projectName;
        output += "\nType of Building: " + typeBuilding;
        output += "\nAddress: " + address;
        output += "\nERF Number: " + erfNum;
        output += "\nTotal Cost: " + df.format(cost);
        output += "\nAmount Paid: " + df.format(totalPaid);
        output += "\nDeadline: " + deadline;
        // If the project is finalised, adds the completion date
        if (finalised){
            output += "\nFinalised?: Yes";
            output += "\nCompletion Date: " + completeDate;}
        else{
            output += "\nFinalised?: No";}
        output += "\n\nCustomer Details:\n" + customer;
        output += "\n\nArchitect Details:\n" + architect;
        output += "\n\nContractor Details:\n" + contractor;
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
        activeProject.deadline = newDeadline;
        // Prints a confirmation
        System.out.println("Deadline changed successfully.");
    }

    public static void changePaid(Project activeProject){
        // Asks the user what they would like set the total fee paid to
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to change the total amount paid to?");
        double newPaid = input.nextDouble();
        input.nextLine();
        activeProject.totalPaid = newPaid;
        // Prints a confirmation
        System.out.println("Amount paid changed successfully.");
    }
}
