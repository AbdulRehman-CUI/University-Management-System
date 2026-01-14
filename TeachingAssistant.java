import java.util.*;

class TeachingAssistant extends Student implements FacultyOperations {
    // Attributes
    private String assignedLab;

    // Constructor
    public TeachingAssistant(String name, String id, String email, int phoneNumber, String address, String password, int semester, double cgpa, String assignedLab) {
        super(name, id, email, phoneNumber, address, password, semester, cgpa);
        this.role = "TA";
        this.assignedLab = assignedLab;
    }

    // 1. DashBoard Method
    @Override
    public void displayDashboard() {
        System.out.println("==================================");
        System.out.println("|    TEACHING ASSISTANT DASH     |");
        System.out.println("==================================");
        System.out.println("Name: " + getName());
        System.out.println("CGPA: " + getCgpa());
        System.out.println("Assigned Lab: " + this.assignedLab);
        System.out.println("Role: Student (with Faculty Privileges)");
        System.out.println("**********************************");
    }

    // 2. Implementing Faculty Operations
    @Override
    public void assignGrade(String studentId, String courseCode, double marks) {
        if (this.getId().equals(studentId)) {
            System.out.println("Your Cannot Assign Grade to Yourself!");
            return;
        }
        System.out.println("[TA] " + getName() + " assigned grade " + marks + " to " + studentId);
    }

    @Override
    public void takeAttendance(String courseCode, String date) {
        System.out.println("[TA] " + getName() + " taking attendance for Lab " + this.assignedLab);
    }

    @Override
    public void viewCourseReport(String courseCode) {
        System.out.println("[TA] Generating limited report for " + courseCode);
    }

    // Extending the methods from Student Class
    @Override
    public void payFee(double amount) {
        if (this.isFeeCleared()) {
            System.out.println("Fee Paid Already...");
            return;
        }

        double discount = 0.50;
        double amountToPay = amount * discount;

        System.out.println("--- TA Fee Processing ---");
        System.out.println("Original Fee: $" + amount);
        System.out.println("TA Discount:  50%");
        System.out.println("Final Amount: $" + amountToPay);

        super.payFee(amountToPay);
    }

    // Method to grade other students
    public void performRoleSpecificActions() {
        // We use a local scanner, but be careful not to close System.in
        Scanner input = new Scanner(System.in);
        boolean sessionActive = true;

        System.out.println("\n*** WELCOME TEACHING ASSISTANT " + " ***");

        while (sessionActive) {
            System.out.println("\n--- TA ACTION MENU ---");
            System.out.println("1. View My Dashboard");
            System.out.println("2. Assign Grade to Student");
            System.out.println("3. Logout");
            System.out.print("Choose Action: ");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        this.displayDashboard();
                        break;
                    case 2:
                        System.out.print("Enter Student ID to Grade: ");
                        String targetId = input.nextLine();
                        System.out.print("Enter Course Code: ");
                        String course = input.nextLine();
                        System.out.print("Enter Marks: ");
                        if (input.hasNextDouble()) {
                            double marks = input.nextDouble();
                            input.nextLine();
                            // Call the method above (which handles the self-grading check)
                            this.assignGrade(targetId, course, marks);
                        } else {
                            System.out.println("Invalid Marks input.");
                            input.nextLine();
                        }
                        break;
                    case 3:
                        sessionActive = false;
                        System.out.println("Logging out from TA Portal...");
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                input.next();
            }
        }
    }
}
