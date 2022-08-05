import java.text.DecimalFormat;

public class Building {

    // Attributes
    private String typeBuilding;
    private String address;
    private String erfNum;
    private double cost;

    // Constructor
    public Building(String typeBuilding, String address,
                    String erfNum, double cost){
        this.setTypeBuilding(typeBuilding);
        this.setAddress(address);
        this.setErfNum(erfNum);
        this.setCost(cost);
    }
    // toString
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "";
        output += "\nType of Building: " + getTypeBuilding();
        output += "\nAddress: " + getAddress();
        output += "\nERF Number: " + getErfNum();
        output += "\nTotal Cost: " + df.format(getCost());
        return output;
    }
    // Getters and Setters
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
}
