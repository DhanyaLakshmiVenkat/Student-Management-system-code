import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("====== Student Management System ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            System.out.println(); // Line break

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    manager.updateStudent();
                    break;
                case 4:
                    manager.deleteStudent();
                    break;
                case 5:
                    System.out.println("Thank you for using the Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("❗ Invalid choice. Please try again.");
            }

            System.out.println(); // Extra line between iterations
        } while (choice != 5);

        scanner.close();
    }
}
