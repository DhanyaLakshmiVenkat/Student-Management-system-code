import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class studentGUI {
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField idField, nameField, ageField, courseField;
    private ArrayList<Student> students = new ArrayList<>();

    public studentGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel - Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        JButton viewButton = new JButton("View Students");
        inputPanel.add(viewButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Center Panel - Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add Button Functionality
        addButton.addActionListener(e -> addStudent());

        // View Button Functionality
        viewButton.addActionListener(e -> viewStudents());

        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            Student student = new Student(id, name, age, course);
            students.add(student);

            displayArea.setText("✅ Student added successfully!\n");
            clearFields();
        } catch (NumberFormatException ex) {
            displayArea.setText("❌ Invalid input! Please enter numeric values for ID and Age.\n");
        }
    }

    private void viewStudents() {
        StringBuilder builder = new StringBuilder();
        for (Student student : students) {
            builder.append("ID: ").append(student.getId()).append("\n")
                    .append("Name: ").append(student.getName()).append("\n")
                    .append("Age: ").append(student.getAge()).append("\n")
                    .append("Course: ").append(student.getCourse()).append("\n")
                    .append("-------------------------\n");
        }

        displayArea.setText(builder.length() > 0 ? builder.toString() : "No students found.");
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
    }

    // Main method to run GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(studentGUI::new);
    }
}
