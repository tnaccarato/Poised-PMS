public class Person {
    // Attributes
    String role;
    String firstName;
    String surname;
    String phoneNum;
    String email;
    String address;

    // Methods

    // Constructor
    public Person (String role, String firstName, String surname, String phoneNum, String email,
                   String address){
        this.role = role;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
    }

    // toString
    public String toString (){
        String output = "Role: " + role;
        output += "\nName: " + firstName + " " + surname;
        output += "\nPhone Number: " + phoneNum;
        output += "\nEmail: " + email;
        output += "\n Address: " + address;
        return output;
    }
}