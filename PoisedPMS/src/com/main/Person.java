package com.main;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * The type Person.
 */
public class Person {
    // Attributes
    private String role;
    private String firstName;
    private String surname;
    private String phoneNum;
    private String email;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    // Methods

    /**
     * Instantiates a new Person.
     *
     * @param role      the role
     * @param firstName the first name
     * @param surname   the surname
     * @param phoneNum  the phone num
     * @param email     the email
     * @param address   the address
     */
// Constructor
    public Person(String role, String id, String firstName, String surname, String phoneNum, String email,
                  String address) {
        this.setRole(role);
        this.setId(id);
        this.setFirstName(firstName);
        this.setSurname(surname);
        this.setPhoneNum(phoneNum);
        this.setEmail(email);
        this.setAddress(address);
    }

    /**
     * Update Person details.
     *
     * @param activePerson the active person
     */
    public static void changeDetails(Person activePerson, Statement statement) throws SQLException {
        // Asks which details the user wants to change
        Scanner changeDetailsScanner = new Scanner(System.in);
        System.out.println("""
                Which details would you like to change?
                n - Name
                p - Phone number
                e - Email Address
                a - Address
                """);
        String changeDetailsInput = changeDetailsScanner.nextLine();
        // If user enters n, allow them to change the person's name
        if ("n".equals(changeDetailsInput)) {
            System.out.println("What would you like to change their first name to?");
            String newFirstName = changeDetailsScanner.nextLine();
            System.out.println("What would you like to change their surname to?");
            String newSurname = changeDetailsScanner.nextLine();
            activePerson.setFirstName(newFirstName);
            activePerson.setSurname(newSurname);
            // Gets Person objects role and ID type
            // (which is capitalised to match column in table)
            String role = activePerson.getRole();
            String idType = role.toUpperCase()+"_ID";
            // Updates the corresponding database entry
            statement.executeUpdate("UPDATE " + role + " SET " + role.toUpperCase()+"_FNAME=\""
                    + newFirstName+"\" WHERE " + idType + "=" + activePerson.getId()+";");
            statement.executeUpdate("UPDATE " + role + " SET " + role.toUpperCase()+"_LNAME=\""
                    +newSurname+"\" WHERE " + idType + "=" + activePerson.getId()+";");
            // Prints a confirmation
            System.out.println("Name changed successfully.");
        }
        // If user enters p, allow them to change person's phone number
        else if ("p".equals(changeDetailsInput)) {
            System.out.println("What would you like to change their phone number to?");
            String newPhoneNum = changeDetailsScanner.nextLine();
            activePerson.setPhoneNum(newPhoneNum);
            // Gets Person objects role and ID type
            // (which is capitalised to match column in table)
            String role = activePerson.getRole();
            String idType = role.toUpperCase()+"_ID";
            // Changes the corresponding database entry
            statement.executeUpdate("UPDATE " + role + " SET " + role.toUpperCase()+"_PHONE=\""
                    + newPhoneNum + "\" WHERE " + idType + "=" + activePerson.getId()+";");
            System.out.println("Phone number changed successfully.");
        }
        // If user enters e, allows them to change person's email
        else if ("e".equals(changeDetailsInput)) {
            System.out.println("What would you like to change their email address to?");
            String newEmail = changeDetailsScanner.nextLine();
            activePerson.setEmail(newEmail);
            // Gets Person objects role and ID type
            // (which is capitalised to match column in table)
            String role = activePerson.getRole();
            String idType = role.toUpperCase()+"_ID";
            // Changes the corresponding database entry
            statement.executeUpdate("UPDATE " + role + " SET " + role.toUpperCase()+"_EMAIL=\""
                    + newEmail + "\" WHERE " + idType + "=" + activePerson.getId()+";");
            // Prints a confirmation
            System.out.println("Email changed successfully.");
        }
        // If user enters a, allows them to change person's address
        else if ("a".equals(changeDetailsInput)) {
            System.out.println("What would you like to change their address to");
            String newAddress = changeDetailsScanner.nextLine();
            activePerson.setAddress(newAddress);
            // Gets Person objects role and ID type
            // (which is capitalised to match column in table)
            String role = activePerson.getRole();
            String idType = role.toUpperCase()+"_ID";
            // Changes the corresponding database entry
            statement.executeUpdate("UPDATE " + role + " SET " + role.toUpperCase()
                    + "_ADDRESS=\"" + newAddress + "\" WHERE " + idType + "="
                    + activePerson.getId()+";");
            // Prints a confirmation
            System.out.println("Address changed successfully.");
        } else {
            System.out.println("Sorry, your choice \"" + changeDetailsInput +
                    "\" was not recognised, " + "please try again.");
        }
    }


    /**
     * Overrides toString method for the object to print attributes
     * @return easy-to-read string of attributes
     */
    public String toString() {
        String output = "\nRole: " + getRole();
        output += "\nID: " + getId();
        output += "\nName: " + getFirstName() + " " + getSurname();
        output += "\nPhone Number: " + getPhoneNum();
        output += "\nEmail: " + getEmail();
        output += "\nAddress: " + getAddress();
        return output;
    }

    // Getters and Setters

    /**
     * Gets role.
     *
     * @return the role
     */

    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets phone num.
     *
     * @return the phone num
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets phone num.
     *
     * @param phoneNum the phone num
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
}
