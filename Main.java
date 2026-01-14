import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    // Creating Objects
    private static Department currentDept;
    // private static Course course;

    // Main Method
    public static void main(String[] args) {
        System.out.println("=== UNIVERSITY MANAGEMENT SYSTEM BOOTING... ===");

        // Play a Sound at the Start
        // SoundManager.playSound("src/startup2.wav");

        Department loadDept = DataManager.loadData();

        if(loadDept != null){
            currentDept = loadDept;
            System.out.println("Session for the Department : " + currentDept.getDeptName());
        }
        else {
            System.out.print("Enter Department Name (e.g., Computer Science): ");
            String deptName = scanner.nextLine();
            System.out.print("Enter Department Code (e.g., CS): ");
            String deptCode = scanner.nextLine();

            currentDept = new Department(deptName, deptCode);
        }

        // Input Data of Students and Teaching Assistant
        if(currentDept.getStudentList().isEmpty()) {
            System.out.println("\n[System] No students found. seeding demo data...");
            seedDemoData();
            seedData();
        }

        // Main Menu Loop
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register New Student (User Input)");
            System.out.println("2. Add New Course (User Input)");
            System.out.println("3. View Department Report");
            System.out.println("4. Simulation: TA Grading Logic Test");
            System.out.println("5. Export Readable Report");
            System.out.println("6. Login to Your System");
            System.out.println("7. Exit");
            System.out.print("Select Any Option of the Above: ");

            int choice = validateIntegerInput();

            switch (choice) {
                case 1:
                    registerStudentInteractive();
                    DataManager.saveData(currentDept);
                    //DataManager.loadData();
                    break;
                case 2:
                    addCourseInteractive();
                    DataManager.saveData(currentDept);
                    break;
                case 3:
                    currentDept.departmentReport();
                    break;
                case 4:
                    performTAGradingTest();
                    break;
                case 5:
                    System.out.println("Generating text Report");
                    DataManager.exportReportToText(currentDept);
                    break;
                case 6:
                    performLoginSystem();
                    break;
                case 7:
                    // SoundManager.playSound("src/preview.wav");
                    System.out.println("Saving Data to File...");
                    DataManager.saveData(currentDept);
                    System.out.println("System Shutting Down...");
                    try{
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option. Try again.");
            }
        }
        scanner.close();
    }

    // 1. Register a New Student via User Input
    private static void registerStudentInteractive() {
        System.out.println("\n--- NEW STUDENT FORM ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();

        String email = name.toLowerCase() + "@uni.edu";

        System.out.print("Semester (Int): ");
        int sem = validateIntegerInput();

        System.out.print("CGPA (Double): ");
        double cgpa = scanner.nextDouble();
        scanner.nextLine();

        Student newStudent = new Student(name, id, email, 0, "Hostel", "pass123", sem, cgpa);

        // Play Sound on the completion of Method Execution
        // SoundManager.playSound("src/levelcomplete.wav");

        // Add to File
        currentDept.addStudent(newStudent);
        // course.addStudent(newStudent);
        System.out.println("Success: Student " + name + " registered.");
    }

    // 2. Add a New Course via User Input
    private static void addCourseInteractive() {
        System.out.println("\n--- NEW COURSE FORM ---");
        System.out.print("Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Course Name: ");
        String cName = scanner.nextLine();
        System.out.print("Max Capacity: ");
        int cap = validateIntegerInput();

        Course newCourse = new Course(code, cName, cap);
        currentDept.addCourse(newCourse);

        // Play Sound on the completion of method execution
        // SoundManager.playSound("src/levelcomplete.wav");
    }

    // 3. Login Method
    private static void performLoginSystem() {
        System.out.println("\n--- STUDENT LOGIN PORTAL ---");

        System.out.print("Enter Student ID: ");
        String inputId = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String inputPass = scanner.nextLine().trim();

        boolean isFound = false;

        // Iterate through all students in the department
        for (Student s : currentDept.getStudentList()) {
            if (s.getId().equalsIgnoreCase(inputId)) {
                isFound = true;

                // Use the existing login method in Person class
                boolean accessGranted = s.login(inputId, inputPass);

                if (accessGranted) {
                    System.out.println("\n[ACCESS GRANTED] Redirecting to Dashboard...");
                    // SoundManager.playSound("src/levelcomplete.wav");

                    // 1. Check if the user is a Teaching Assistant
                    if(s instanceof TeachingAssistant) {
                        // Calls the specific TA menu (Grading, etc.)
                        ((TeachingAssistant) s).performRoleSpecificActions();
                    }
                    // 2. If not a TA, they are a Regular Student
                    else {
                        // Calls the Student menu (Dashboard, Change Password, etc.)
                        s.performStudentMenu();
                    }
                } else {
                    System.out.println("\n[FAILED] Incorrect Password.");
                }
                break;
            }
        }

        if (!isFound) {
            System.out.println("\n[ERROR] User ID not found in database.");
        }
    }

    // TA Grading the Students Mehtod
    private static void performTAGradingTest() {
        System.out.println("\n--- TESTING TA LOGIC ---");
        TeachingAssistant ta = new TeachingAssistant("Mr Ali", "TA001", "john@uni.edu", 12345, "City", "pass", 5, 3.8, "Lab 1");

        System.out.println("Created TA: " + ta.getName() + " (ID: TA001)");

        System.out.println("\nTest 1: TA grading another student...");
        ta.assignGrade("STU999", "CS101", 85);

        System.out.println("\nTest 2: TA grading THEMSELVES...");
        ta.assignGrade("TA001", "CS101", 100);

        // Playing the Sound
        // SoundManager.playSound("src/levelcomplete.wav");
    }

    // Pre-loads data for the Faculty Class
    private static void seedData() {
        Faculty f1 = new Faculty("Dr. Asfar", "FAC01", "asfar@uni.edu", 999, "Office 1", "pass1", "CS", 50000);
        Faculty f2 = new Faculty("Dr. Sikandar", "FAC02", "sikandar@uni.edu", 998, "Office 2", "pass2", "CS", 55000);
        Faculty f3 = new Faculty("Dr. Irfan", "FAC03", "irfan@uni.edu", 997, "Office 3", "pass3", "CS", 40000);
        Faculty f4 = new Faculty("Dr. Aslam", "FAC04", "aslam@uni.edu", 996, "Office 4", "pass4", "CS", 50000);
        Faculty f5 = new Faculty("Dr. Akram", "FAC05", "akram@uni.edu", 995, "Office 5", "pass5", "CS", 60000);
        currentDept.addFaculty(f1);
        currentDept.addFaculty(f2);
        currentDept.addFaculty(f3);
        currentDept.addFaculty(f4);
        currentDept.addFaculty(f5);

        Course c1 = new Course("CS101", "Intro to OOP", 60);
        Course c2 = new Course("CS102", "Intro to Bioinformatics", 50);
        Course c3 = new Course("CS103", "Intro to Programming", 60);
        Course c4 = new Course("CS104", "Discreter Structures", 50);
        Course c5 = new Course("CS105", "Technical and Business Writing", 60);

        c1.assignTeacher(f1);
        c2.assignTeacher(f1);
        c3.assignTeacher(f1);
        c4.assignTeacher(f1);
        c5.assignTeacher(f1);

        currentDept.addCourse(c1);
        currentDept.addCourse(c2);
        currentDept.addCourse(c3);
        currentDept.addCourse(c4);
        currentDept.addCourse(c5);

        DataManager.saveData(currentDept);
        DataManager.exportReportToText(currentDept);
        System.out.println("The Data related to the Faculty and Course has been saved!");
    }

    // Helper to prevent crash if user enters text instead of number
    private static int validateIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid Input. Please enter a number.");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    // Storing the Students and TA Data
    private static void seedDemoData() {
        System.out.println("--- Seeding 5 Students and 1 TA ---");

        // 1. Create 5 Students
        Student s1 = new Student("Minhaj Ul Siraj", "S101", "minhaj@uni.edu", 111222, "Street A", "pass101", 1, 3.5);
        Student s2 = new Student("Hamza Shahid",   "S102", "hamza@uni.edu",  333444, "Street B", "pass102", 2, 3.2);
        Student s3 = new Student("Husnain Abbas",   "S103", "husnain@uni.edu",  555666, "Street C", "pass103", 3, 3.9);
        Student s4 = new Student("Abdul Rehman",    "S104", "abdulrehman@uni.edu",  777888, "Street D", "pass104", 4, 3.0);
        Student s5 = new Student("Abdul Moiz",  "S105", "abdulmoiz@uni.edu", 999000, "Street E", "pass105", 1, 2.5);

        // 2. Create 1 Teaching Assistant
        TeachingAssistant ta1 = new TeachingAssistant("Rachel Zane", "TA500", "rachel@uni.edu", 121212, "Lab View", "tapass", 6, 3.8, "Chemistry Lab");

        // 3. Add to Department
        currentDept.addStudent(s1);
        currentDept.addStudent(s2);
        currentDept.addStudent(s3);
        currentDept.addStudent(s4);
        currentDept.addStudent(s5);
        currentDept.addStudent(ta1); // Polymorphism: TA is treated as a Student here

        System.out.println("Success: 5 Students and 1 TA added to database.");

        // 4. Save to File using DataManager
        DataManager.saveData(currentDept);
        DataManager.exportReportToText(currentDept);
    }
}