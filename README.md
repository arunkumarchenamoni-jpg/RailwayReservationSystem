# Railway Reservation System


# Project Description
The Railway Reservation System is a Java-based console application used to manage railway ticket booking operations. It allows users to book tickets, cancel tickets, check seat availability, manage passenger details, and handle waiting list records.

This project uses MySQL database connectivity through JDBC to store and retrieve train, passenger, and ticket information.

# Features
- Ticket booking
- Ticket cancellation
- Seat availability checking
- Waiting list handling
- Passenger details management
- MySQL database connectivity
- JDBC connection with Java
- File handling for ticket records
- Exception handling for invalid inputs
- Multithreading concept implementation

# Technologies Used
- Java
- MySQL
- JDBC
- Notepad++
- Git
- GitHub

# How to Run
First, clone the repository using the command `git clone YOUR_REPOSITORY_LINK`. After cloning, open the project folder using the command `cd RailwayReservationSystem`.

Next, open MySQL Workbench and create a database using `CREATE DATABASE railwaydb;` and select it using `USE railwaydb;`.

After setting up the database, open Command Prompt in the project folder and compile the Java files using:

javac -cp ".;mysql-connector-j-9.7.0.jar" *.java

Then run the project using:

java -cp ".;mysql-connector-j-9.7.0.jar" Main

If your main class name is different, replace `Main` with your actual main class name.

# Conclusion
This project helped in understanding how a railway reservation system works in real time. It improved practical knowledge of Java programming, OOP concepts, JDBC connectivity, MySQL database operations, file handling, exception handling, multithreading, Git, and GitHub.
