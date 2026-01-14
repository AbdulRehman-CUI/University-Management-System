class Faculty extends Person implements FacultyOperations{
    // Specific Attributes
    private String department;
    private double salary;
    private int coursesTaught;

    // Constructor
    public Faculty(String name, String id, String email, int phoneNumber, String address, String password, String department, double salary) {
        super(name, id, email, phoneNumber, address, "FACULTY", password);
        this.department = department;
        if(salary < 0){
            System.out.println("The salary of the faculty should be +ve.");
        }
        else {
            this.salary = salary;
        }
        this.coursesTaught = 0;
    }

    // Getters
    public String getDepartment() { return department; }
    public double getSalary(){ return salary;}

    // Implementing the abstract method from Person Class
    @Override
    public void displayDashboard() {
        System.out.println("==================================");
        System.out.println("|      FACULTY DASHBOARD         |");
        System.out.println("==================================");
        System.out.println("Name: " + this.getName());
        System.out.println("Dept: " + this.department);
        System.out.println("Salary: $" + this.salary);
        System.out.println("Courses Taught: " + this.coursesTaught);
        System.out.println("**********************************");
    }

    // 1. Method to define workload(Courses Taught)
    public void incrementCourseLoad(){
        this.coursesTaught++;
        System.out.println("Courses updated for " + this.getName());
    }

    // 2. Method to Decrement the workload
    public void decrementCourseLoad(){
        if(this.coursesTaught > 0){
            this.coursesTaught--;
            System.out.println("Courses decreased for " + this.getName());
        }
    }

    // 3. Implementing the assignGrade method from Interface FacultyOperations
    @Override
    public void assignGrade(String studentId, String courseCode, double marks) {
        if (marks < 0 || marks > MAX_MARKS) {
            System.out.println("Error: Invalid marks. Max allowed is " + MAX_MARKS);
        }
        else {
            System.out.println("Grade Assigned: Student " + studentId + " got " + marks + " in " + courseCode);
        }
    }

    // 4. Attendance
    @Override
    public void takeAttendance(String courseCode, String date) {
        System.out.println("Attendance taken for " + courseCode + " on " + date);
    }

    // 5. Course Report
    @Override
    public void viewCourseReport(String courseCode) {
        System.out.println("Generating report for " + courseCode + "...");
        System.out.println("Department : " + department);
    }
}