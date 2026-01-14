import java.util.ArrayList;
import java.util.*;

class Student extends Person{
    // Attributes
    private double cgpa;
    private int semester;
    private boolean isFeePaid;
    private ArrayList<String> enrolledCourses;

    // Constructor
    public Student(String name, String id, String email, int phoneNumber, String address, String password, int semester, double cgpa) {
        super(name, id, email, phoneNumber, address, "STUDENT", password);
        this.semester = semester;
        this.cgpa = cgpa;
        this.isFeePaid = false;
        this.enrolledCourses = new ArrayList<>();
    }

    // Getters
    public double getCgpa() { return cgpa; }
    public int getSemester() { return semester; }
    public ArrayList<String> getEnrolledCourses(){ return new ArrayList<>(this.enrolledCourses);}
    // Helper method to calculate just the number of enrolled courses is at the end of this class

    // 1. Implementing the abstract Method from Person Class
    @Override
    public void displayDashboard() {
        System.out.println("==================================");
        System.out.println("|     STUDENT DASHBOARD          |");
        System.out.println("==================================");
        System.out.println("Student Name: " + this.getName());
        System.out.println("Student ID:   " + this.getId());
        System.out.println("Semester:     " + this.semester);
        System.out.println("CGPA:         " + this.cgpa);
        System.out.println("Fee Status:   " + (this.isFeePaid ? "Paid" : "Pending"));
        System.out.println("Enrolled In:  " + this.enrolledCourses);
        System.out.println("***********************************");
    }

    // 2. Methods related to Student Class
    public void registerCourse(String courseCode) {
        if(enrolledCourses.contains(courseCode)){
            System.out.println("You are already registered for this course.");
            return;
        }
        if (isFeePaid) {
            enrolledCourses.add(courseCode);
            System.out.println("Success: Enrolled in " + courseCode);
        }
        else {
            System.out.println("Error: Cannot register, Please pay your fees first.");
        }
    }

    // 3. Pay Fee Method
    public void payFee(double amount) {
        if(isFeePaid){
            System.out.println("Fee Paid Already...");
        }
        if (amount > 0) {
            this.isFeePaid = true;
            System.out.println("Fee Paid Successfully.");
        }
        else {
            System.out.println("Invalid amount, Your fee amount should be positive.");
        }
    }

    // 4. Method to claculate the number of courses the student is enrolled in
    public int getCourseCount() {
        return enrolledCourses.size();
    }

    // 5. Method to Check whether the free is paid or not?
    public boolean isFeeCleared(){
        return isFeePaid;
    }

    // Student Menu
    public void performStudentMenu() {
        Scanner input = new Scanner(System.in);
        boolean active = true;

        System.out.println("\n*** WELCOME STUDENT " + this.getName() + " ***");

        while (active) {
            System.out.println("\n--- STUDENT PORTAL ---");
            System.out.println("1. View My Dashboard");
            System.out.println("2. Change Password"); // New Option
            System.out.println("3. Logout");
            System.out.print("Select Option: ");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        this.displayDashboard();
                        break;
                    case 2:
                        // Logic to change password
                        System.out.print("Enter Old Password: ");
                        String oldPass = input.nextLine();
                        System.out.print("Enter New Password (min 8 chars): ");
                        String newPass = input.nextLine();

                        // Calls the method in Person.java
                        this.changePassword(oldPass, newPass);
                        break;
                    case 3:
                        active = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            } else {
                System.out.println("Invalid input.");
                input.next();
            }
        }
    }
}
