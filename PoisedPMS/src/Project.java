import java.time.LocalDate;

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
                   String erfNum, double cost, double totalPaid, LocalDate deadline, boolean finalised,
                   Person customer, Person contractor, Person architect, LocalDate completeDate){
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
        String output = "Project Number: " + projectNum;
        output += "\nProject Name: " + projectName;
        output += "\nType of Building: " + typeBuilding;
        output += "\nAddress: " + address;
        output += "\nERF Number: " + erfNum;
        output += "\nTotal Cost: " + cost;
        output += "\nAmount Paid: " + totalPaid;
        output += "\nDeadline: " + deadline;
        output += "\nFinalised?: " + finalised;
        if (finalised){
            output += "\nCompletion Date: " + completeDate;}
        output += "\nCustomer Details:\n" + customer;
        output += "\nArchitect Details:\n" + architect;
        output += "\nContractor Details:\n" + contractor;
        return output;
    }
}

