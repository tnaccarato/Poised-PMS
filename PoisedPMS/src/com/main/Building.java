package com.main;

import java.text.DecimalFormat;

/**
 * The type Building.
 */
public class Building {

    // Attributes
    private String typeBuilding;
    private String address;
    private String erfNum;
    private double cost;

    /**
     * Instantiates a new Building.
     *
     * @param typeBuilding the type building
     * @param address      the address
     * @param erfNum       the erf num
     * @param cost         the cost
     */
// Constructor
    public Building(String typeBuilding, String address,
                    String erfNum, double cost) {
        this.setTypeBuilding(typeBuilding);
        this.setAddress(address);
        this.setErfNum(erfNum);
        this.setCost(cost);
    }

    /**
     * Gets the values of each field and writes them to a string.
     *
     * @return the string
     */
// Gets the values of each field and writes them to a string
    public String getAttributes() {
        String attributes;
        attributes = getTypeBuilding() + "," + getAddress() + "," + getErfNum() + "," + getCost();
        return attributes;
    }

    // toString
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##"); // Declares a DecimalFormat for costs
        String output = "";
        output += "\nType of com.main.Building: " + getTypeBuilding();
        output += "\nAddress: " + getAddress();
        output += "\nERF Number: " + getErfNum();
        output += "\nTotal Cost: " + df.format(getCost());
        return output;
    }

    // Getters and Setters

    /**
     * Gets type building.
     *
     * @return the type building
     */

    public String getTypeBuilding() {
        return typeBuilding;
    }

    /**
     * Sets type building.
     *
     * @param typeBuilding the type building
     */
    public void setTypeBuilding(String typeBuilding) {
        this.typeBuilding = typeBuilding;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets erf num.
     *
     * @return the erf num
     */
    public String getErfNum() {
        return erfNum;
    }

    /**
     * Sets erf num.
     *
     * @param erfNum the erf num
     */
    public void setErfNum(String erfNum) {
        this.erfNum = erfNum;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
