import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Add a new student
    public void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // consume newline

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        Student student = new Student(id, name, age, course);
        students.add(student);
        System.out.println("✅ Student added successfully!\n");
        saveToFile();
    }

    // View all students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student student : students) {
                student.displayInfo();
            }
        }
    }

    // Update a student
    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // consume newline

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                found = true;

                System.out.print("Enter new name: ");
                student.setName(scanner.nextLine());

                System.out.print("Enter new age: ");
                student.setAge(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter new course: ");
                student.setCourse(scanner.nextLine());

                System.out.println("✅ Student updated successfully!\n");
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Student not found.\n");
        }
        saveToFile();
    }

    // Delete a student
    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = students.removeIf(student -> student.getId() == id);

        if (removed) {
            System.out.println("✅ Student deleted successfully!\n");
        } else {
            System.out.println("❌ Student not found.\n");
        }
        saveToFile();
    }
    public void loadFromFile() {
        File file = new File("students.txt");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String course = parts[3];
                    students.add(new Student(id, name, age, course));
                }
            }
            System.out.println("📂 Loaded students from file.\n");
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // Save students to file
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                bw.write(student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.getCourse());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}
