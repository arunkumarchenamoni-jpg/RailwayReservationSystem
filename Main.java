import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;

// ---------------- THREAD CLASS ----------------

class BookingThread extends Thread {

    String passengerName;

    BookingThread(String passengerName) {

        this.passengerName = passengerName;
    }

    public void run() {

        System.out.println(
                passengerName + " booking started..."
        );

        try {

            Thread.sleep(2000);

        }

        catch(Exception e) {

            System.out.println(e);
        }

        System.out.println(
                passengerName + " booking completed"
        );
    }
}

// ---------------- TRAIN CLASS ----------------

class Train {

    int trainNo;
    String trainName;
    String source;
    String destination;

    int totalSeats;
    int availableSeats;

    Train(int trainNo,
          String trainName,
          String source,
          String destination,
          int totalSeats) {

        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;

        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }
}

// ---------------- PASSENGER CLASS ----------------

class Passenger {

    int passengerId;
    String passengerName;
    int age;

    Passenger(int passengerId,
              String passengerName,
              int age) {

        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.age = age;
    }
}

// ---------------- TICKET CLASS ----------------

class Ticket {

    int pnr;

    Passenger passenger;

    Train train;

    int seatNo;

    String coachType;

    String journeyDate;

    int fare;

    String status;

    Ticket(int pnr,
           Passenger passenger,
           Train train,
           int seatNo,
           String coachType,
           String journeyDate,
           int fare,
           String status) {

        this.pnr = pnr;

        this.passenger = passenger;

        this.train = train;

        this.seatNo = seatNo;

        this.coachType = coachType;

        this.journeyDate = journeyDate;

        this.fare = fare;

        this.status = status;
    }
}

// ---------------- MAIN CLASS ----------------

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Train> trains =
            new ArrayList<>();

    static ArrayList<Ticket> tickets =
            new ArrayList<>();

    static Queue<Passenger> waitingList =
            new LinkedList<>();

    static int seatCounter = 1;

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        addDefaultTrains();

        while(true) {

            try {

                displayMenu();

                int choice = sc.nextInt();

                switch(choice) {

                    case 1:

                        viewTrains();

                        break;

                    case 2:

                        bookTicket();

                        break;

                    case 3:

                        cancelTicket();

                        break;

                    case 4:

                        checkSeatAvailability();

                        break;

                    case 5:

                        viewBookedTickets();

                        break;

                    case 6:

                        viewWaitingList();

                        break;

                    case 7:

                        adminAddTrain();

                        break;

                    case 8:

                        System.out.println(
                                "Exiting..."
                        );

                        System.exit(0);

                    default:

                        System.out.println(
                                "Invalid Choice"
                        );
                }
            }

            catch(Exception e) {

                System.out.println(
                        "Invalid Input Entered"
                );

                sc.nextLine();
            }
        }
    }

    // ---------------- DEFAULT TRAINS ----------------

    static void addDefaultTrains() {

        trains.add(
                new Train(
                        101,
                        "Express",
                        "Hyderabad",
                        "Delhi",
                        3
                )
        );

        trains.add(
                new Train(
                        102,
                        "Superfast",
                        "Chennai",
                        "Mumbai",
                        2
                )
        );

        trains.add(
                new Train(
                        103,
                        "Intercity",
                        "Bangalore",
                        "Pune",
                        4
                )
        );
    }

    // ---------------- MENU ----------------

    static void displayMenu() {

        System.out.println(
                "\n===== Railway Reservation System ====="
        );

        System.out.println("1. View Trains");

        System.out.println("2. Book Ticket");

        System.out.println("3. Cancel Ticket");

        System.out.println("4. Check Seat Availability");

        System.out.println("5. View Booked Tickets");

        System.out.println("6. View Waiting List");

        System.out.println("7. Admin Add Train");

        System.out.println("8. Exit");

        System.out.print("Enter your choice: ");
    }

    // ---------------- VIEW TRAINS ----------------

    static void viewTrains() {

        System.out.println("\nAvailable Trains:");

        for(Train t : trains) {

            System.out.println(
                    "\nTrain Number: " + t.trainNo
            );

            System.out.println(
                    "Train Name: " + t.trainName
            );

            System.out.println(
                    "Source: " + t.source
            );

            System.out.println(
                    "Destination: " + t.destination
            );

            System.out.println(
                    "Available Seats: "
                            + t.availableSeats
            );
        }
    }

    // ---------------- BOOK TICKET ----------------

    static void bookTicket() {

        try {

            System.out.print(
                    "Enter Train Number: "
            );

            int trainNo = sc.nextInt();

            Train selectedTrain = null;

            for(Train t : trains) {

                if(t.trainNo == trainNo) {

                    selectedTrain = t;

                    break;
                }
            }

            if(selectedTrain == null) {

                System.out.println(
                        "Train Not Found"
                );

                return;
            }

            System.out.print(
                    "Enter Passenger ID: "
            );

            int id = sc.nextInt();

            sc.nextLine();

            System.out.print(
                    "Enter Passenger Name: "
            );

            String name = sc.nextLine();

            System.out.print(
                    "Enter Passenger Age: "
            );

            int age = sc.nextInt();

            sc.nextLine();

            System.out.print(
                    "Enter Journey Date: "
            );

            String date = sc.nextLine();

            System.out.println(
                    "Choose Coach Type"
            );

            System.out.println("1. Sleeper");

            System.out.println("2. AC");

            int coachChoice = sc.nextInt();

            String coachType;

            int fare;

            if(coachChoice == 1) {

                coachType = "Sleeper";

                fare = 300;
            }

            else {

                coachType = "AC";

                fare = 1000;
            }

            // THREAD

            BookingThread bt =
                    new BookingThread(name);

            bt.start();

            Passenger p =
                    new Passenger(id, name, age);

            if(selectedTrain.availableSeats > 0) {

                Random r = new Random();

                int pnr =
                        100000 + r.nextInt(900000);

                Ticket t = new Ticket(
                        pnr,
                        p,
                        selectedTrain,
                        seatCounter++,
                        coachType,
                        date,
                        fare,
                        "CONFIRMED"
                );

                tickets.add(t);

                selectedTrain.availableSeats--;

                System.out.println(
                        "\nTicket Booked Successfully"
                );

                System.out.println(
                        "PNR Number: " + t.pnr
                );

                System.out.println(
                        "Seat Number: " + t.seatNo
                );

                System.out.println(
                        "Coach Type: " + t.coachType
                );

                System.out.println(
                        "Fare: " + t.fare
                );

                saveTicketToFile(t);

                saveToDatabase(t);
            }

            else {

                waitingList.add(p);

                System.out.println(
                        "No Seats Available"
                );

                System.out.println(
                        "Passenger Added to Waiting List"
                );
            }
        }

        catch(Exception e) {

            System.out.println(
                    "Invalid Input During Booking"
            );

            sc.nextLine();
        }
    }

    // ---------------- SAVE TO DATABASE ----------------

    static void saveToDatabase(Ticket t) {

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            Connection con =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/railwaydb",
                            "root",
                            "root123"
                    );

            // INSERT PASSENGER

            String passengerQuery =
                    "INSERT INTO passengers(passenger_id, passenger_name, age) VALUES (?, ?, ?)";

            PreparedStatement ps1 =
                    con.prepareStatement(passengerQuery);

            ps1.setInt(
                    1,
                    t.passenger.passengerId
            );

            ps1.setString(
                    2,
                    t.passenger.passengerName
            );

            ps1.setInt(
                    3,
                    t.passenger.age
            );

            ps1.executeUpdate();

            // INSERT TICKET

            String ticketQuery =
                    "INSERT INTO tickets(pnr, passenger_id, train_no, seat_no, coach_type, journey_date, fare, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps2 =
                    con.prepareStatement(ticketQuery);

            ps2.setInt(
                    1,
                    t.pnr
            );

            ps2.setInt(
                    2,
                    t.passenger.passengerId
            );

            ps2.setInt(
                    3,
                    t.train.trainNo
            );

            ps2.setInt(
                    4,
                    t.seatNo
            );

            ps2.setString(
                    5,
                    t.coachType
            );

            ps2.setString(
                    6,
                    t.journeyDate
            );

            ps2.setInt(
                    7,
                    t.fare
            );

            ps2.setString(
                    8,
                    t.status
            );

            ps2.executeUpdate();

            System.out.println(
                    "Data Saved Into MySQL Database Successfully"
            );

            con.close();
        }

        catch(Exception e) {

            System.out.println(
                    "Database Error:"
            );

            e.printStackTrace();
        }
    }

    // ---------------- SAVE TICKET TO FILE ----------------

    static void saveTicketToFile(Ticket t) {

        try {

            FileWriter fw =
                    new FileWriter(
                            "tickets.txt",
                            true
                    );

            fw.write(
                    "\nPNR: " + t.pnr
            );

            fw.write(
                    "\nPassenger Name: "
                            + t.passenger.passengerName
            );

            fw.write(
                    "\nTrain Name: "
                            + t.train.trainName
            );

            fw.write(
                    "\nSeat Number: "
                            + t.seatNo
            );

            fw.write(
                    "\nCoach Type: "
                            + t.coachType
            );

            fw.write(
                    "\nJourney Date: "
                            + t.journeyDate
            );

            fw.write(
                    "\nFare: "
                            + t.fare
            );

            fw.write(
                    "\nStatus: "
                            + t.status
            );

            fw.write(
                    "\n------------------------\n"
            );

            fw.close();

            System.out.println(
                    "Ticket Saved To File"
            );
        }

        catch(IOException e) {

            System.out.println(
                    "File Error"
            );
        }
    }

    // ---------------- CANCEL TICKET ----------------

    static void cancelTicket() {

        try {

            System.out.print(
                    "Enter PNR Number: "
            );

            int cancelPnr = sc.nextInt();

            boolean found = false;

            for(int i = 0;
                i < tickets.size();
                i++) {

                if(tickets.get(i).pnr
                        == cancelPnr) {

                    Train train =
                            tickets.get(i).train;

                    tickets.remove(i);

                    train.availableSeats++;

                    found = true;

                    System.out.println(
                            "Ticket Cancelled Successfully"
                    );

                    break;
                }
            }

            if(!found) {

                System.out.println(
                        "PNR Not Found"
                );
            }
        }

        catch(Exception e) {

            System.out.println(
                    "Invalid Input During Cancellation"
            );

            sc.nextLine();
        }
    }

    // ---------------- SEAT AVAILABILITY ----------------

    static void checkSeatAvailability() {

        try {

            System.out.print(
                    "Enter Train Number: "
            );

            int trainNo = sc.nextInt();

            for(Train t : trains) {

                if(t.trainNo == trainNo) {

                    System.out.println(
                            "Available Seats: "
                                    + t.availableSeats
                    );

                    return;
                }
            }

            System.out.println(
                    "Train Not Found"
            );
        }

        catch(Exception e) {

            System.out.println(
                    "Invalid Train Number"
            );

            sc.nextLine();
        }
    }

    // ---------------- VIEW BOOKED TICKETS ----------------

    static void viewBookedTickets() {

        if(tickets.isEmpty()) {

            System.out.println(
                    "No Tickets Booked"
            );
        }

        else {

            for(Ticket t : tickets) {

                System.out.println(
                        "\nPNR: " + t.pnr
                );

                System.out.println(
                        "Passenger Name: "
                                + t.passenger.passengerName
                );

                System.out.println(
                        "Age: "
                                + t.passenger.age
                );

                System.out.println(
                        "Train: "
                                + t.train.trainName
                );

                System.out.println(
                        "Journey Date: "
                                + t.journeyDate
                );

                System.out.println(
                        "Seat Number: "
                                + t.seatNo
                );

                System.out.println(
                        "Coach Type: "
                                + t.coachType
                );

                System.out.println(
                        "Fare: "
                                + t.fare
                );

                System.out.println(
                        "Status: "
                                + t.status
                );
            }
        }
    }

    // ---------------- WAITING LIST ----------------

    static void viewWaitingList() {

        if(waitingList.isEmpty()) {

            System.out.println(
                    "Waiting List Empty"
            );
        }

        else {

            System.out.println(
                    "\nWaiting Passengers:"
            );

            for(Passenger p : waitingList) {

                System.out.println(
                        p.passengerName
                );
            }
        }
    }

    // ---------------- ADMIN ADD TRAIN ----------------

    static void adminAddTrain() {

        try {

            System.out.print(
                    "Enter Train Number: "
            );

            int no = sc.nextInt();

            sc.nextLine();

            System.out.print(
                    "Enter Train Name: "
            );

            String name = sc.nextLine();

            System.out.print(
                    "Enter Source: "
            );

            String source = sc.nextLine();

            System.out.print(
                    "Enter Destination: "
            );

            String destination = sc.nextLine();

            System.out.print(
                    "Enter Total Seats: "
            );

            int seats = sc.nextInt();

            trains.add(
                    new Train(
                            no,
                            name,
                            source,
                            destination,
                            seats
                    )
            );

            System.out.println(
                    "Train Added Successfully"
            );
        }

        catch(Exception e) {

            System.out.println(
                    "Invalid Train Details"
            );

            sc.nextLine();
        }
    }
}