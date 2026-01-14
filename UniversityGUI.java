import javax.swing.*;
import java.awt.*;

public class UniversityGUI extends JFrame {

    // Logic Objects (Backend)
    private Department currentDept;

    // GUI Components (Frontend)
    private JTextArea displayArea;

    public UniversityGUI() {
        // --- 1. SETUP DATA ---
        // Load existing data or create a new Department if none exists
        currentDept = DataManager.loadData();
        if (currentDept == null) {
            currentDept = new Department("Computer Science", "CS");
        }

        // --- 2. SETUP WINDOW (JFrame) ---
        setTitle("University Management System");
        setSize(900, 600); // Width, Height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop program when 'X' is clicked
        setLayout(new BorderLayout()); // Layout Manager

        // --- 3. CENTER: DISPLAY AREA ---
        // This JTextArea replaces System.out.println
        displayArea = new JTextArea();
        displayArea.setEditable(false); // User cannot type here, only read
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Code-like font
        displayArea.setText("Welcome to University Management System!\n" +
                "Department: " + currentDept.getDeptName() + "\n" +
                "==========================================\n");

        // Wrap the text area in a ScrollPane so we can scroll if text gets long
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // --- 4. BOTTOM: BUTTON PANEL ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Buttons arranged in a row

        // Create Buttons
        JButton btnAddStudent = new JButton("Register Student");
        JButton btnAddCourse = new JButton("Add Course");
        JButton btnViewReport = new JButton("View Report");
        JButton btnExport = new JButton("Export to Text");
        JButton btnSaveExit = new JButton("Save & Exit");

        // Add Buttons to the Panel
        buttonPanel.add(btnAddStudent);
        buttonPanel.add(btnAddCourse);
        buttonPanel.add(btnViewReport);
        buttonPanel.add(btnExport);
        buttonPanel.add(btnSaveExit);

        // Add Panel to the Bottom of the Window
        add(buttonPanel, BorderLayout.SOUTH);

        // --- 5. BUTTON ACTIONS (Listeners) ---

        // Action: Register Student
        btnAddStudent.addActionListener(e -> {
            registerStudentPopup();
        });

        // Action: Add Course
        btnAddCourse.addActionListener(e -> {
            addCoursePopup();
        });

        // Action: View Report
        btnViewReport.addActionListener(e -> {
            // We use the existing getReportString() method from your Department class
            String report = currentDept.getReportString();
            displayArea.setText(report);
            // SoundManager.playSound("src/levelcomplete.wav");
        });

        // Action: Export
        btnExport.addActionListener(e -> {
            DataManager.exportReportToText(currentDept);
            JOptionPane.showMessageDialog(this, "Report Exported Successfully!");
        });

        // Action: Save & Exit
        btnSaveExit.addActionListener(e -> {
            // SoundManager.playSound("src/preview.wav");
            DataManager.saveData(currentDept);
            JOptionPane.showMessageDialog(this, "Data Saved! Exiting...");
            System.exit(0);
        });
    }

    // --- HELPER METHODS FOR INPUT POPUPS ---

    private void registerStudentPopup() {
        // Create input fields
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField semField = new JTextField();
        JTextField cgpaField = new JTextField();

        // Arrange them in an Object array for the popup
        Object[] message = {
                "Student Name:", nameField,
                "Student ID:", idField,
                "Semester (Number):", semField,
                "CGPA (Decimal):", cgpaField
        };

        // Show the popup
        int option = JOptionPane.showConfirmDialog(null, message, "Register New Student", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // 1. Get data from fields
                String name = nameField.getText();
                String id = idField.getText();
                int sem = Integer.parseInt(semField.getText()); // Convert text to int
                double cgpa = Double.parseDouble(cgpaField.getText()); // Convert text to double

                String email = name.toLowerCase().replaceAll(" ", "") + "@uni.edu";

                // 2. Create Student Object
                Student s = new Student(name, id, email, 0, "Hostel", "pass123", sem, cgpa);

                // 3. Add to Dept
                currentDept.addStudent(s);

                // 4. Feedback
                displayArea.append("\n[Success] Student " + name + " registered.\n");
                // SoundManager.playSound("src/levelcomplete.wav");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers for Semester and CGPA.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void addCoursePopup() {
        JTextField nameField = new JTextField();
        JTextField codeField = new JTextField();
        JTextField capField = new JTextField();

        Object[] message = {
                "Course Name:", nameField,
                "Course Code:", codeField,
                "Max Capacity:", capField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Course", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String code = codeField.getText();
                int cap = Integer.parseInt(capField.getText());

                Course c = new Course(code, name, cap);
                currentDept.addCourse(c);

                displayArea.append("\n[Success] Course " + name + " added.\n");
                // SoundManager.playSound("src/levelcomplete.wav");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input! Please check your numbers.");
            }
        }
    }

    // --- MAIN METHOD TO RUN GUI ---
    public static void main(String[] args) {

        // Play Sound at the start of the Program
        // SoundManager.playSound("src/startup2.wav");

        SwingUtilities.invokeLater(() -> {
            UniversityGUI gui = new UniversityGUI();
            gui.setVisible(true); // Make the window visible
        });
    }
}