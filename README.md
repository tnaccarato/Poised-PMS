# poised-pms

A project management system for a small structural engineering firm using OOP.
This will be updated later on in the course to add more functionality.

## UML

![UML](/Poised%20UML.png "UML")

## Menu

On start, the user is given the following menu:

a  - Add a new project\
v  - View all projects\
cd - Change the due date of the project\
cp - Change the amount the client has paid to date\
uc - Update the contact details of the contractor\
f  - Finalise a project\
q  - Quit the program

## Features

### Add a New Project

If the user enters 'a', allow the user to enter new details about a project.
The user is asked for the following:

- Project number
- Project name
- What type of building is being designed? E.g. House, apartment block or
store, etc
- The physical address for the project
- ERF number
- The total fee being charged for the project
- The total amount paid to date
- Deadline for the project
- The name, telephone number, email address and physical address of the
architect for the project
- The name, telephone number, email address and physical address of the
contractor for the project
- The name, telephone number, email address and physical address of the
customer for the project

### View All Projects

If the user selects 'v', displays the information about all the projects in the
list in an easy to read format.

### Change Due Date

If the user selects 'cd', allows the user to choose a project number to change
the deadline for.

### Change Amount Paid

If the user selects 'cp', allows the user to change the amount the client has
paid for.

### Update Contractor

If the user selects 'uc', allows them to change any of the contact details
of the contractor.

### Finalise Projects

If the user selects 'f', allows them to mark all current projects as finalised,
adds the current date as the completion date, prints the amount the client still
has to pay, and prints an invoice if there is still an amount to be paid.

### Quit

If the user selects 'q', quits the program.
