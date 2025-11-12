package l_t_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetManagerApp {
    private static final List<User> users = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("\nBudget Manager - Welcome!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = safeInt(scanner);

                if (choice == 1) {
                    registerUser(scanner);
                } else if (choice == 2) {
                    loginUser(scanner);
                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\nBudget Manager - Logged in as " + currentUser.getUsername());
                System.out.println("4. Add Income");
                System.out.println("5. Add Expense");
                System.out.println("6. View Summary");
                System.out.println("7. Add/Update Profile");
                System.out.println("8. Logout");
                System.out.print("Enter your choice: ");
                int choice = safeInt(scanner);

                switch (choice) {
                    case 4 -> addIncome(scanner);
                    case 5 -> addExpense(scanner);
                    case 6 -> viewSummary();
                    case 7 -> upsertProfile(scanner);
                    case 8 -> currentUser = null;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        scanner.close();
    }

    // --- Auth ---
    private static void registerUser(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.next();
        if (findUser(username) != null) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter a password: ");
        String password = scanner.next();

        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        User found = findUser(username);
        if (found != null && found.getPassword().equals(password)) {
            currentUser = found;
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private static User findUser(String username) {
        for (User u : users) if (u.getUsername().equals(username)) return u;
        return null;
    }

    // --- Features ---
    private static void addIncome(Scanner scanner) {
        System.out.print("Source: ");
        String source = scanner.next();
        System.out.print("Amount: ");
        double amount = safeDouble(scanner);
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.next();

        currentUser.addIncome(new IncomeTransaction(source, amount, date));
        System.out.println("Income added successfully!");
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Category: ");
        String category = scanner.next();
        System.out.print("Amount: ");
        double amount = safeDouble(scanner);
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.next();
        scanner.nextLine(); // consume newline
        System.out.print("Description: ");
        String description = scanner.nextLine();

        currentUser.addExpense(new ExpenseTransaction(category, amount, date, description));
        System.out.println("Expense added successfully!");
    }

    private static void viewSummary() {
        double totalIncome = currentUser.getTotalIncome();
        double totalExpense = currentUser.getTotalExpense();
        double net = currentUser.getNetBalance();

        System.out.println("\n=== Summary ===");
        System.out.printf("Total Income : %.2f%n", totalIncome);
        System.out.printf("Total Expense: %.2f%n", totalExpense);
        System.out.printf("Net Balance  : %.2f%n", net);
    }

    private static void upsertProfile(Scanner scanner) {
        scanner.nextLine(); // clear buffer
        System.out.print("Full name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        currentUser.setDetails(new UserDetails(name, email));
        System.out.println("Profile saved.");
    }

    // --- Safe input ---
    private static int safeInt(Scanner sc) {
        while (!sc.hasNextInt()) { System.out.print("Enter a number: "); sc.next(); }
        return sc.nextInt();
    }

    private static double safeDouble(Scanner sc) {
        while (!sc.hasNextDouble()) { System.out.print("Enter a number: "); sc.next(); }
        return sc.nextDouble();
    }
}
