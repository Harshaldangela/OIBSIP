package onlinereservation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ReservationSys {
    private static Map<String, User> users = new HashMap<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("Enter a new username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.err.println("Username already exists. Choose a different one.");
            return;
        }

        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        users.put(username, user);
        System.out.println("Registration Successful!");
    }

    private static void login(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(hashPassword(password))) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Logout");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        makeReservation(scanner, username);
                        break;
                    case 2:
                        cancelReservation(scanner, username);
                        break;
                    case 3:
                        System.out.println("Logging out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void makeReservation(Scanner scanner, String username) {
        System.out.println("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.println("Enter class type: (1/2/General)");
        String classtype = scanner.nextLine();
        System.out.println("Enter journey date: (DD/MM/YY)");
        String journeyDate = scanner.nextLine();
        System.out.println("Enter Leaving From: ");
        String source = scanner.nextLine();
        System.out.println("Enter Going To: ");
        String destination = scanner.nextLine();

        String pnr = "PNR" + trainNumber + " " + pnrCounter++;
        Reservation reservation = new Reservation(pnr, username, trainNumber, classtype, journeyDate, source, destination);
        reservations.add(reservation);

        System.out.println("Reservation successful!");
        System.out.println("PNR: " + pnr);
    }

    private static void cancelReservation(Scanner scanner, String username) {
        System.out.println("Enter PNR number to cancel: ");
        String pnr = scanner.nextLine();

        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getUsername().equals(username) && reservation.getPnr().equals(pnr)) {
                iterator.remove();
                System.err.println("Reservation canceled successfully!");
                return;
            }
        }
        System.out.println("Reservation not found or you do not have permission to cancel.");
    }

    // Hash password for login verification (same hashing used during registration)
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString(); // Return hashed password as hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password hashing error", e);
        }
    }
}
