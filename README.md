# Poised Project Management System

A project management system for a small structural engineering firm using OOP.
The program reads data from the projects.txt file (or creates it if it does not
exist) and generates a list from it, allowing the user to perform a range of
functions.

## Menu

On start, the user is given the following menu:

a  - Add a new project\
s  - Search for a particular project\
va - View all projects\
vi - View incomplete projects\
vo - View overdue projects\
cd - Change the due date of the project\
cp - Change the amount the client has paid to date\
uc - Update the contact details\
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

This data is then appended to the projects.txt and projectList.

### View All Projects

If the user selects 'v', displays the information about all the projects in the
list in an easy to read format.

### View Incomplete Projects

If the user selects 'vi', displays the information about any projects that have
not been marked as finalised.

### View Overdue Projects

If the user selects 'vo', displays the information about any projects that are
past their due date.

### Change Due Date

If the user selects 'cd', allows the user to choose a project number to change
the deadline for, given the project number.

### Change Amount Paid

If the user selects 'cp', allows the user to change the amount the client has
paid for, given the project number.

### Update Contact Details

If the user selects 'uc', allows them to change any of the contact details
for a Person object of their choice, given the project number.

### Finalise Projects

If the user selects 'f', allows them to mark a project (given the project
number)as finalised, adds the current date as the completion date, prints the
amount the client still has to pay, and prints an invoice if there is still an
amount to be paid.

### Quit

If the user selects 'q', quits the program.
