package com.main;

/**
 * The type Building.
 */
public class Building {

    // Attributes
    private String typeBuilding;
    private String address;
    private String erfNum;

    /**
     * Instantiates a new Building.
     *
     * @param typeBuilding the type building
     * @param address      the address
     * @param erfNum       the erf num
     */
// Constructor
    public Building(String typeBuilding, String address,
                    String erfNum){
        this.setTypeBuilding(typeBuilding);
        this.setAddress(address);
        this.setErfNum(erfNum);
    }


    /**
     * Overrides toString method to print the details of the object for viewing
     * @return string with object details for use in print statements
     */
    public String toString() {
        String output = "";
        output += "\nType of Building: " + getTypeBuilding();
        output += "\nAddress: " + getAddress();
        output += "\nERF Number: " + getErfNum();
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
}